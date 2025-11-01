package com.example.orders.services;

import com.example.generated.fetchers.types.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    private final Map<String, Order> orders;

    public OrdersService() {
        this.orders = new ConcurrentHashMap<>(Map.of(
                "11", Order.newBuilder().clientId("1").total(200).build(),
                "22", Order.newBuilder().clientId("1").total(500).build(),
                "33", Order.newBuilder().clientId("1").total(200).build()
        ));
    }

    public Order getOrder(String id) {
        return orders.get(id);
    }

    public Map<String, Order> getAllOrders() {
        return Map.copyOf(orders);
    }

    public List<Order> getOrdersByClientId(String clientId) {
        return orders.values().stream().filter(o -> o.getClientId().equals(clientId)).collect(Collectors.toList());
    }
}
