package dihtiar.sasha.service;

import dihtiar.sasha.model.HPlace;

import dihtiar.sasha.model.Session;
import dihtiar.sasha.model.Ticket;
import dihtiar.sasha.repository.SessionRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionFactory sessionFactory;

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

    @Transactional
    @Override
    public void addSession(Session session) {
        org.hibernate.Session ses = sessionFactory.openSession();
        Query query = ses.createQuery("from Session where month(start) =: monthParam and year(start) =:yearParam and day(start) =:dayParam");
        int month = session.getStart().toLocalDateTime().getMonthValue();
        int year = session.getStart().toLocalDateTime().getYear();
        int day = session.getStart().toLocalDateTime().getDayOfMonth();
        query.setParameter("monthParam", month);
        query.setParameter("yearParam", year);
        query.setParameter("dayParam", day);
        List<Session> list = query.list();
        boolean flag = false;
        if (list.isEmpty()) {
            flag = true;
        } else {
            for (Session s : list) {
                if (s.getHall().getId() == session.getHall().getId()) {
                    if (session.getStart().getTime() > s.getStart().getTime() + new Timestamp(s.getFilm().getDuration() * 60000).getTime() ||
                            s.getStart().getTime() > session.getStart().getTime() + new Timestamp(session.getFilm().getDuration() * 60000).getTime()) {
                        flag = true;
                    } else {
                        flag = false;
                        break;
                    }
                } else {
                    flag = true;
                }
            }
        }
        if (flag == true) {
            sessionRepository.save(session);
        }

    }

    @Transactional
    @Override
    public void deleteSession(String name, Timestamp time, String hall_name) {
        sessionRepository.deleteSessionByFilm_NameAndStartAndHall_Name(name, time, hall_name);
    }

    @Override
    public Session findSessiontByFIlmNameAndStartAndHall_name(String film_name, Timestamp start, String hall_name) {
        return sessionRepository.findSessionByFilm_NameAndStartAndHall_Name(film_name, start, hall_name);
    }

    @Override
    public List<HPlace> freePlace(Session session) {
        List<HPlace> list = new ArrayList<>();
        List<HPlace> list2 = hPlaceService.findHPByHall(session.getHall());
        List<Ticket> tickets = ticketService.getTickets();
        if (tickets.size() > 0) {
            for (Ticket t : tickets) {
                if (t.getSession().getId() == session.getId()) {
                    list.add(t.gethPlace());
                }
            }
        }
        list2.removeAll(list);
        return list2;
    }

}
