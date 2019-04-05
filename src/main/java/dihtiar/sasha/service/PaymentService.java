package dihtiar.sasha.service;

import dihtiar.sasha.model.Payment;
import dihtiar.sasha.model.Users;

public interface PaymentService {

    void addPayment(Payment payment);

    Payment showYourPayment(Users user);
}
