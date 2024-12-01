package com.api_manager.clinic.services;

import com.api_manager.clinic.domain.entity.Client;
import org.springframework.stereotype.Service;
import com.api_manager.clinic.repository.ClientRepository;

import java.util.Optional;

@Service
public class ClientServiceImp implements ClientService{

    private final ClientRepository clientRepository;


    public ClientServiceImp(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Iterable<Client> listClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client findClientById(long id) throws Exception {
        Optional<Client> optionalClient =clientRepository.findById(id);

        if(optionalClient.isPresent())
            return optionalClient.get();
        else
            throw new Exception("Client Not Found");

    }
}
