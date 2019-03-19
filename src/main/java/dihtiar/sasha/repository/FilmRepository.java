package dihtiar.sasha.repository;

import dihtiar.sasha.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Film findFilmByName(String name);

    void deleteByName(String name);
}
