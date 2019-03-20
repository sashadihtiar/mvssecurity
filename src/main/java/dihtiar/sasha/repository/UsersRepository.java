package dihtiar.sasha.repository;

import dihtiar.sasha.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findUsersById(Long id);
    void deleteUsersById(Long id);
    Users findUsersByLogin(String login);
}