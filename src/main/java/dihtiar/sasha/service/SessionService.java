package dihtiar.sasha.service;

import dihtiar.sasha.model.HPlace;
import dihtiar.sasha.model.Session;

import java.sql.Time;
import java.util.List;

public interface SessionService {
    List<Session> getAll();

    List<Session> findSessionByFilmName(String name);

    void addSession(Session session);

    void deleteSession(String name, Time time);

    Session findSessiontByFIlmNameAdnStart(String film_name, Time start);

    List<HPlace> freePlace(Session session);
}
