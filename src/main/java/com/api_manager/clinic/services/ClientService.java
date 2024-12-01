package com.api_manager.clinic.services;

import com.api_manager.clinic.domain.entity.Client;

public interface ClientService {
    Iterable<Client> listClients();
    Client findClientById(long id) throws Exception;
}
