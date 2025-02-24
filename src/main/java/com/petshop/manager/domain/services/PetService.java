package com.petshop.manager.domain.services;

import com.petshop.manager.data.dto.ClienteDTO;
import com.petshop.manager.data.dto.PetDTO;
import com.petshop.manager.domain.model.Pet;
import com.petshop.manager.domain.model.Usuario;
import com.petshop.manager.domain.repositories.PetRepository;
import com.petshop.manager.domain.services.base.BaseService;
import com.petshop.manager.domain.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService extends BaseService<Pet, PetDTO> {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MapperUtil mapper;

    @Autowired
    public PetService(PetRepository repository) {
        super(repository, PetDTO.class, Pet.class);
        this.petRepository = repository;
    }

    @Override
    public PetDTO gravar(PetDTO dto) {
        if(dto.getCliente() == null) {
            Usuario user = (Usuario) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            ClienteDTO clienteDTO = clienteService.getDto(user.getId());
            dto.setCliente(clienteDTO);
        }
        return super.gravar(dto);
    }

    @Override
    public List<PetDTO> listar() {
        Usuario user = (Usuario) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        List<Pet> list;
        if ("Admin".equalsIgnoreCase(user.getPerfil())) {
            return mapper.mapAll(super.listar(), PetDTO.class);
        } else {
            list = this.petRepository.findByCriadoPor_IdOrCliente_Id(user.getId(), user.getId());
        }
        return mapper.mapAll(list, PetDTO.class);
    }

}
