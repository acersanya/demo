package com.kapsch.demo.com.kapsch.service;

import com.kapsch.demo.com.kapsch.model.Ticket;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    public Ticket persist(Double price, String client) {
        return Ticket
            .builder()
            .price(price)
            .client(client)
            .build();
    }
}
