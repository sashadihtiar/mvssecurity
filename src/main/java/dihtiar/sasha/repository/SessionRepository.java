package dihtiar.sasha.repository;

import dihtiar.sasha.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    void deleteSessionByFilm_NameAndStartAndHall_Name(String name_film, Timestamp start, String hall_name);

    Session findSessionByFilm_NameAndStartAndHall_Name(String film_name, Timestamp start, String hall_name);

    List<Session> findSessionsByFilm_Name(String filmName);
}
