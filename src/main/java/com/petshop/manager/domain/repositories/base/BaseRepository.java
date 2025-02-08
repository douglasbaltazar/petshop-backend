package com.petshop.manager.domain.repositories.base;

import com.petshop.manager.domain.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 *
 * @param <T> - Entidade
 * @param <K> - Tipo Chave Primária.
 */

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, K> extends PagingAndSortingRepository<T,K>, JpaSpecificationExecutor<T>, JpaRepository<T, K> {

}
