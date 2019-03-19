package dihtiar.sasha.service;

import dihtiar.sasha.model.Hall;

import java.util.List;

public interface HallService {

    List<Hall> getAll();

    void addnewHall(Hall hall);

    void deleteHall(String name);

    Hall findHallByName(String name);
}
