package dihtiar.sasha.service;

import dihtiar.sasha.model.Users;

import java.util.List;

public interface UsersService {

    List<Users> getAll();

    void addUser(Users users);

    Users findUserById(Long id);

    void deleteUser(Long id);

    Users findUserByLogin(String login);

    Double checkDiscount(Users user);

}