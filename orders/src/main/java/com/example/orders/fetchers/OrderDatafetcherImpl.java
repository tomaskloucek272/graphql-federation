package com.example.orders.fetchers;

import com.example.generated.fetchers.datafetchers.OrderDatafetcher;
import com.example.generated.fetchers.types.Client;
import com.example.generated.fetchers.types.Order;
import com.example.orders.services.OrdersService;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsEntityFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderDatafetcherImpl extends OrderDatafetcher {

    private final OrdersService orderService;

    public OrderDatafetcherImpl(OrdersService orderService) {
        this.orderService = orderService;
    }


    @Override
    public Order getOrder(DataFetchingEnvironment dataFetchingEnvironment) {
        System.out.println("OrderDatafetcherImpl: getOrder");
        return null;
    }

    @DgsEntityFetcher(name = "Client")
    public Client client(Map<String, Object> values) {
        System.out.println("OrderDatafetcherImpl: client");
        return Client.newBuilder().id((String) values.get("id")).build();
    }

    @DgsData(parentType = "Client", field = "orders")
    public List<Order> resolveOrders(DgsDataFetchingEnvironment dataFetchingEnvironment)  {
        System.out.println("OrderDatafetcherImpl: resolveOrders");
        Client client = dataFetchingEnvironment.getSource();
        return orderService.getOrdersByClientId(client.getId());
    }
}
