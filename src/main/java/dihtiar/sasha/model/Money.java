package dihtiar.sasha.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class Money implements Serializable {
    @Column(name = "amount")
    private Double amountMoney;

    @ManyToOne
    @JoinColumn(name = "DICTIONARY_ID")
    private My—urrency my—urrency;

    public Money(Double amountMoney, My—urrency my—urrency) {
        this.amountMoney = amountMoney;
        this.my—urrency = my—urrency;
    }

    public Double getAmountMoney() {
        return amountMoney;
    }

    public My—urrency getMy—urrency() {
        return my—urrency;
    }

    protected Money() {
    }
}
