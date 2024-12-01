package com.api_manager.clinic.repository;


import com.api_manager.clinic.domain.entity.Client;
import org.springframework.data.repository.CrudRepository;


public interface ClientRepository extends CrudRepository<Client, Long> {
}
