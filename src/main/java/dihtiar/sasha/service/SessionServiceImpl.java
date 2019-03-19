package dihtiar.sasha.service;

import dihtiar.sasha.model.Session;
import dihtiar.sasha.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public List<Session> getAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Session findSessionByFilmName(String name) {
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
}
