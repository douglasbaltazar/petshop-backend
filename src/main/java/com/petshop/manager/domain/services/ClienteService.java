package com.petshop.manager.domain.services;

import com.petshop.manager.data.dto.ClienteDTO;
import com.petshop.manager.domain.model.Cliente;
import com.petshop.manager.domain.model.Usuario;
import com.petshop.manager.domain.repositories.AtendimentoRepository;
import com.petshop.manager.domain.repositories.ClienteRepository;
import com.petshop.manager.domain.services.base.BaseService;
import com.petshop.manager.domain.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService extends BaseService<Cliente, ClienteDTO> {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    MapperUtil mapper;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        super(repository, ClienteDTO.class, Cliente.class);
        this.clienteRepository = repository;
    }

    @Override
    public List<ClienteDTO> listar() {
        Usuario user = (Usuario) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        List<Cliente> list;
        if ("Admin".equalsIgnoreCase(user.getPerfil())) {
            list = clienteRepository.findAll();
        } else {
            list = this.clienteRepository.findByUsuario(user);
        }
        return mapper.mapAll(list, ClienteDTO.class);
    }
}
