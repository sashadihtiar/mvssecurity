package dihtiar.sasha.service;

import dihtiar.sasha.model.*;

import dihtiar.sasha.repository.SessionRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SessionServiceImpl implements SessionService {
    @Autowired
    UsersService usersService;

    @Autowired
    MyCurrencyService myCurrencyService;

    @Autowired
    MyPropertiesService myPropertiesService;

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

    @Transactional
    @Override
    public List<HPlace> freePlace(Session session) {
        List<HPlace> list = new ArrayList<>();
        List<HPlace> list2 = hPlaceService.findHPByHall(session.getHall());
        List<Ticket> tickets = ticketService.getTickets();
        if (!tickets.isEmpty()) {
            for (Ticket t : tickets) {
                if (t.getSession().getId() == session.getId()) {
                    list.add(t.gethPlace());
                }
            }
        }
        list2.removeAll(list);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object obj = auth.getPrincipal();
        String username = "";
        username = ((Users) obj).getLogin();

        Users user = usersService.findUserByLogin(username);
        Double costd = Double.valueOf(myPropertiesService.findProps("default.cost").getValue());
        if (session.getStart().toLocalDateTime().getHour() < 10) {
            costd *= Double.valueOf(myPropertiesService.findProps("default.coffortime").getValue());
        } else if (session.getStart().toLocalDateTime().getHour() > 17) {
            costd *= 1 + Double.valueOf(myPropertiesService.findProps("default.coffortime").getValue());
        }
        for (HPlace hp : list2) {
            if (hp.getR() == 1) {
                hp.setCost(new Money(costd * Double.valueOf(myPropertiesService.
                        findProps("default.cofforplace").getValue()),
                        myCurrencyService.
                                findByID(Long.valueOf(myPropertiesService.findProps("default.currency").getValue()))));
            } else if (list2.size() > 0 && hp.getR() == list2.get(list2.size() - 1).getR()) {
                hp.setCost(new Money(costd * (1 + Double.valueOf(myPropertiesService.
                        findProps("default.cofforplace").getValue())),
                        myCurrencyService.
                                findByID(Long.valueOf(myPropertiesService.findProps("default.currency").getValue()))));
            } else {
                hp.setCost(new Money(costd, myCurrencyService.
                        findByID(Long.valueOf(myPropertiesService.findProps("default.currency").getValue()))));
            }
            if (usersService.checkDiscount(user) != 0) {
                hp.setCost(new Money(hp.getCost().getAmountMoney() - hp.getCost().getAmountMoney() * usersService.checkDiscount(user),
                        hp.getCost().getMyCurrency()));
            }
        }
        return list2;
    }

}
