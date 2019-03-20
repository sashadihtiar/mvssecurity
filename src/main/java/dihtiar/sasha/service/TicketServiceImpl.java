package dihtiar.sasha.service;

import dihtiar.sasha.model.Ticket;
import dihtiar.sasha.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getyourTicket(String login) {
        return ticketRepository.findTicketByUsers_Login(login);
    }

    @Transactional
    @Override
    public void addTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }
}
