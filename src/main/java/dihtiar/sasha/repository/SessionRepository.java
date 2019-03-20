package dihtiar.sasha.repository;

import dihtiar.sasha.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findSessionByFilm_Name(String name);

    void deleteSessionByFilm_NameAndStart(String name, Time start);

    Session findSessionByFilm_NameAndStart(String film_name, Time start);
}
