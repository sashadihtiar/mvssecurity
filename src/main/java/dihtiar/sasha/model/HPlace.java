package dihtiar.sasha.model;

import javax.persistence.*;

@Entity
@Table(name = "hallplace")
public class HPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "r")
    private Long r;

    @Column(name = "p")
    private Long p;

    @ManyToOne
    @JoinColumn(name = "id_hall")
    private Hall hall;

    @Transient
    private Money cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getR() {
        return r;
    }

    public void setR(Long r) {
        this.r = r;
    }

    public Long getP() {
        return p;
    }

    public void setP(Long p) {
        this.p = p;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Money getCost() {
        return cost;
    }

    public void setCost(Money cost) {
        this.cost = cost;
    }
}
