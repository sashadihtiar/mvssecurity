package dihtiar.sasha.service;

import dihtiar.sasha.model.Film;
import dihtiar.sasha.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FilmServiceImpl implements FilmService {
    @Autowired
    FilmRepository filmRepository;

    @Override
    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    @Transactional
    public void deleteFilmByName(String name) {
        filmRepository.deleteByName(name);
    }

    @Transactional
    @Override
    public void addnewFilm(Film film) {
        filmRepository.save(film);
    }

    @Override
    public Film findFilmByName(String name) {
        return filmRepository.findFilmByName(name);
    }
}
