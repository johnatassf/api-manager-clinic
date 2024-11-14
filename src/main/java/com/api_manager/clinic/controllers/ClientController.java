package com.api_manager.clinic.controllers;

import com.api_manager.clinic.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import com.api_manager.clinic.repository.ClientRepository;

import java.util.List;

@RestController

public class ClientController {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/clients")
    List<Client> all() {
        return (List<Client>) clientRepository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/clients")
    Client newClient(@RequestBody Client newClient) {
        return clientRepository.save(newClient);
    }

    // Single item

    @GetMapping("/clients/{id}")
    Client one(@PathVariable Long id) throws Exception {

        return clientRepository.findById(id)
                .orElseThrow(() -> new Exception("id"));
    }

    @PutMapping("/clients/{id}")
    Client replaceClient(@RequestBody Client newClient, @PathVariable Long id) {

        return clientRepository.findById(id)
                .map(Client -> {
                    Client.setName(newClient.getName());
                    Client.setAge(newClient.getAge());
                    return clientRepository.save(Client);
                })
                .orElseGet(() -> {
                    return clientRepository.save(newClient);
                });
    }

    @DeleteMapping("/clients/{id}")
    void deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
    }
}
