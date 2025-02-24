package com.petshop.manager.domain.services.base;

import com.petshop.manager.data.dto.PetDTO;
import com.petshop.manager.data.dto.UsuarioDTO;
import com.petshop.manager.data.dto.base.BaseEntityDTO;
import com.petshop.manager.data.exception.ValidationException;
import com.petshop.manager.domain.model.BaseEntity;
import com.petshop.manager.domain.model.Usuario;
import com.petshop.manager.domain.repositories.base.BaseRepository;
import com.petshop.manager.domain.utils.MapperUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BaseService<T extends BaseEntity, D extends BaseEntityDTO> {
    @Autowired
    private MapperUtil mapper;

    @Autowired
    private BaseRepository<T, Long> repository;

    private final Class<D> dtoClass;
    private final Class<T> entityClass;


    public BaseService(BaseRepository<T, Long> repository, Class<D> dtoClass, Class<T> entityClass) {
        this.repository = repository;
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    public List<D> listar() {
        Usuario user = (Usuario) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        List<T> list;
        if ("Admin".equalsIgnoreCase(user.getPerfil())) {
            list = repository.findAll();
        } else {
            list = this.listarPorUsuario(user.getId());
        }
        return mapper.mapAll(list, dtoClass);
    }

    public List<T> listarPorUsuario(Long id) {
        return new ArrayList<>(repository.findByCriadoPor_Id(id));
    }

    public D gravar(D d) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = null;
        if (authentication != null && authentication.getPrincipal() instanceof Usuario) {
            user = (Usuario) authentication.getPrincipal();
        }
        T t = mapper.map(d, entityClass);
        t.setDataAlteracao(LocalDateTime.now());
        t.setDataCadastro(LocalDateTime.now());
        t.setCriadoPor(user);
        return new MapperUtil().map(repository.save(t), dtoClass);
    }

    @Transactional(rollbackFor = Throwable.class)
    public List<D> gravar(List<D> lista) {
        return StreamSupport.stream(repository.saveAll(mapper.mapAll(lista, entityClass)).spliterator(), false)
                .map(entity -> mapper.map(entity, dtoClass))
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Throwable.class)
    public D atualizar(D dto) {
        T saved = repository.findById(dto.getId())
                .orElseThrow(this::notFound);
        T t = mapper.map(dto, entityClass);
        t.setCriadoPor(saved.getCriadoPor());
        BeanUtils.copyProperties(t, saved);
        saved.setDataAlteracao(LocalDateTime.now());

        return mapper.map(repository.save(saved), dtoClass);
    }

    public ValidationException notFound() {
        return new ValidationException(String.format("%s não localizado.", "Registro"));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public void apagarPorId(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public D getDto(Long id) {
        T t = repository.findById(id).orElseThrow(this::notFound);
        return mapper.map(t, dtoClass);
    }

    public T getEntidade(Long id) {
        return repository.findById(id).orElseThrow(this::notFound);
    }

}
