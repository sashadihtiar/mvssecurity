package dihtiar.sasha.service;


import dihtiar.sasha.Util.Utils;
import dihtiar.sasha.model.Users;
import dihtiar.sasha.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UsersServiceImpl implements UsersService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsersRepository usersRepository;

    @Override
    public List<Users> getAll() {
        Users users = findUserByLogin("CORP");
        List<Users> list = usersRepository.findAll();
        if (users != null) {
            list.remove(users);
        }
        return list;
    }

    @Transactional
    @Override
    public void addUser(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
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

    @Override
    public Double checkDiscount(Users user) {
        int deep = Utils.check(user);
        return (deep - 1) * 0.1;
    }
}