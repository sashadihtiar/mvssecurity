package dihtiar.sasha.service;

import dihtiar.sasha.model.HPlace;
import dihtiar.sasha.model.Hall;

import java.util.List;

public interface HPlaceService {
    void addHPlace(HPlace hPlace);

    HPlace findHPlaceForTicket(String hall_name, Long r, Long p);

    void deleteByHallID(Long id);

    List<HPlace> findHPByHall(Hall hall);

    List<HPlace> showall();

}
