package dihtiar.sasha.service;

import dihtiar.sasha.exception.OutOfMoneyException;
import dihtiar.sasha.model.Ticket;
import dihtiar.sasha.model.Users;

import java.sql.Timestamp;
import java.util.List;

public interface TicketService {

    List<Ticket> getTickets();

    List<Ticket> getyourTicket(String login);

    void addTicket(Ticket ticket);

    void buyTicket(String filmName, Timestamp startFilm, Long rows, Long place, String hallName,Users user) throws OutOfMoneyException;
}
