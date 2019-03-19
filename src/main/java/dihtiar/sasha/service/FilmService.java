package dihtiar.sasha.service;

import dihtiar.sasha.model.Film;

import java.util.List;

public interface FilmService {
    List<Film> getAll();

  void deleteFilmByName(String name);

  void addnewFilm(Film film);

  Film findFilmByName(String name);
}
