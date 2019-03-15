package dihtiar.sasha.service;


import dihtiar.sasha.model.Users;
import dihtiar.sasha.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    @Transactional
    @Override
    public void addUser(Users users) {
        usersRepository.save(users);
    }

    @Override
    public Users findUserById(Long id) {
        return usersRepository.findUsersById(id);
    }

    @Override
    public void deleteUser(Long id) {
        usersRepository.deleteUsersById(id);
    }

    @Override
    public Users findUserByLogin(String login) {
        return usersRepository.findUsersByLogin(login);
    }
}