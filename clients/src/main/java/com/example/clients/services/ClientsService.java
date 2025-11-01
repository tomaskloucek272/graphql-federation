package com.example.clients.services;

import com.example.generated.fetchers.types.Client;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ClientsService {

    private final Map<String, Client> clients;

    public ClientsService() {
        this.clients = new ConcurrentHashMap<>(Map.of(
                "1", Client.newBuilder().id("1").email("Hello@email.cz").name("John Doe").build(),
                "2", Client.newBuilder().id("2").email("Bye@post.cz").name("Mark Doe").build(),
                "3", Client.newBuilder().id("3").email("Greetings@post.cz").name("Mark Doe").build()
        ));
    }

    public Client getClient(String id) {
        return clients.get(id);
    }

    public Map<String, Client> getAllClients() {
        return Map.copyOf(clients);
    }
}
