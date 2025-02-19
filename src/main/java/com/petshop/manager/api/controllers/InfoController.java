package com.petshop.manager.api.controllers;

import com.petshop.manager.data.dto.InfoDTO;
import com.petshop.manager.domain.services.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/infos")
public class InfoController {

    @Autowired
    private InfoService infoService;
    @GetMapping()
    public ResponseEntity<InfoDTO> getInfo() {
        return ResponseEntity.ok(infoService.getInfos());
    }

}
