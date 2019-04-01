package dihtiar.sasha.service;

import dihtiar.sasha.model.HPlace;
import dihtiar.sasha.model.Session;

import java.sql.Timestamp;
import java.util.List;

public interface SessionService {
    List<Session> getAll();

    void addSession(Session session);

    void deleteSession(String name, Timestamp time, String hall_name);

    Session findSessiontByFIlmNameAndStartAndHall_name(String film_name, Timestamp start, String hall_name);

    List<HPlace> freePlace(Session session);
}
