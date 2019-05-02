package dihtiar.sasha.service;

import dihtiar.sasha.model.Payment;
import dihtiar.sasha.model.Users;

import java.util.List;

public interface PaymentService {

    void addPayment(Payment payment);

    List<Payment> showYourPayment(Users user);
}
