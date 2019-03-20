package dihtiar.sasha.service;

import dihtiar.sasha.model.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> getTickets();

    List<Ticket> getyourTicket(String login);

    void addTicket(Ticket ticket);
}
