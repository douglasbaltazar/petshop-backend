package com.petshop.manager.domain.repositories;

import com.petshop.manager.data.dto.InfoDTO;
import com.petshop.manager.domain.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends JpaRepository<Atendimento, Long> {

    @Query(value = "SELECT " +
            "  1 AS id, " +
            "  (SELECT SUM(a.valor) FROM tb_atendimentos a) AS valorArrecadado, " +
            "  (SELECT COUNT(*) FROM tb_pets) AS numPets, " +
            "  (SELECT COUNT(*) FROM tb_racas) AS numRacas, " +
            "  (SELECT COUNT(*) FROM tb_clientes) AS numClientes, " +
            "  (SELECT COUNT(*) FROM tb_atendimentos) AS numAtendimentos",
            nativeQuery = true)
    InfoDTO buscarInfo();


}
