package com.kapsch.demo.com.kapsch.repository;

import com.kapsch.demo.com.kapsch.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
