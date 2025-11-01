package com.example.clients.fetchers;

import com.example.clients.services.ClientsService;
import com.example.generated.fetchers.datafetchers.ClientDatafetcher;
import com.example.generated.fetchers.types.Client;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Service;

@Service
public class ClientDataFetcherImpl extends ClientDatafetcher {

    private final ClientsService clientsService;

    public ClientDataFetcherImpl(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @Override
    public Client getClient(DataFetchingEnvironment dataFetchingEnvironment) {
        System.out.println("Invoking getClient");
        return clientsService.getClient(dataFetchingEnvironment.getArgument("id"));
    }
}
