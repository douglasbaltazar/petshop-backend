package com.petshop.manager.domain.services;

import com.petshop.manager.data.dto.InfoDTO;
import com.petshop.manager.domain.repositories.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    @Autowired
    private InfoRepository infoRepository;

    public InfoDTO getInfos() {
        return infoRepository.buscarInfo();
    }

}
