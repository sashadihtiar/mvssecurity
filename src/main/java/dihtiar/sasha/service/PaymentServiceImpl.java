package dihtiar.sasha.service;

import dihtiar.sasha.model.Payment;
import dihtiar.sasha.model.Users;
import dihtiar.sasha.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentsRepository paymentsRepository;

    @Transactional
    @Override
    public void addPayment(Payment payment) {
        paymentsRepository.save(payment);

    }

    @Override
    public Payment showYourPayment(Users user) {
        return paymentsRepository.findByAccount_Users(user);
    }
}
