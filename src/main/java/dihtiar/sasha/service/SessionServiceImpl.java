package dihtiar.sasha.service;

import dihtiar.sasha.model.HPlace;
import dihtiar.sasha.model.Session;
import dihtiar.sasha.model.Ticket;
import dihtiar.sasha.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    TicketService ticketService;

    @Autowired
    HPlaceService hPlaceService;

    @Override
    public List<Session> getAll() {
        return sessionRepository.findAll();
    }

    @Override
    public List<Session> findSessionByFilmName(String name) {
        return sessionRepository.findSessionByFilm_Name(name);
    }

    @Transactional
    @Override
    public void addSession(Session session) {
        sessionRepository.save(session);
    }

    @Transactional
    @Override
    public void deleteSession(String name, Time time) {
        sessionRepository.deleteSessionByFilm_NameAndStart(name, time);
    }

    @Override
    public Session findSessiontByFIlmNameAdnStart(String film_name, Time start) {
        return sessionRepository.findSessionByFilm_NameAndStart(film_name, start);
    }

    @Override
    public List<HPlace> freePlace(Session session) {
        List<HPlace> list = new ArrayList<>();
        List<HPlace> result = new ArrayList<>();
        List<Ticket> tickets = ticketService.getTickets();
        if (tickets.size() > 0) {
            for (Ticket t : tickets) {
                if (t.getSession().getId() == session.getId()) {
                    HPlace hPlace = t.gethPlace();
                    list.add(hPlace);
                }
            }
            List<HPlace> list2 = hPlaceService.findHPByHall(session.getHall());
            if (list.size() > 0) {
                for (HPlace hp : list2) {
                    for (HPlace HP : list) {
                        if (hp.getR() != HP.getR() || hp.getP() != HP.getP()) {
                            result.add(hp);
                        }
                    }
                }
            } else {
                result = list2;
            }
        } else {
            result = hPlaceService.findHPByHall(session.getHall());
        }
        return result;

    }
}
