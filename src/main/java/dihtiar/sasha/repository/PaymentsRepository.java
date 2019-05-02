package dihtiar.sasha.repository;

import dihtiar.sasha.model.Payment;
import dihtiar.sasha.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByAccount_Users(Users user);
}
