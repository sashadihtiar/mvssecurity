package dihtiar.sasha.repository;

import dihtiar.sasha.model.Payment;
import dihtiar.sasha.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment, Long> {
    Payment findByAccount_Users(Users user);
}
