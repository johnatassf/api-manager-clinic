package com.api_manager.clinic.controllers;

import com.api_manager.clinic.entity.Client;
import com.api_manager.clinic.services.ClientServiceImp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.api_manager.clinic.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientServiceImp clientServiceImp;


    @GetMapping("")
    List<Client> all() {
        return (List<Client>) clientRepository.findAll();
    }

    @PostMapping()
    Client newClient(@RequestBody Client newClient) {
        return clientRepository.save(newClient);
    }

    @GetMapping("/{id}")
    Client one(@PathVariable Long id) throws Exception {

        return clientRepository.findById(id)
                .orElseThrow(() -> new Exception("id"));
    }

    @PutMapping("/{id}")
    Client replaceClient(@RequestBody Client newClient, @PathVariable Long id) {

        return clientRepository.findById(id)
                .map(existingClient -> {
                    BeanUtils.copyProperties(newClient, existingClient, "id"); // Exclude id to avoid overwriting
                    return clientRepository.save(existingClient);
                }).orElseThrow();
    }

    @DeleteMapping("/{id}")
    void deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
    }
}
