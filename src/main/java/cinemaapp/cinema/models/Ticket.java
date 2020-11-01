package cinemaapp.cinema.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ticket")
public class Ticket {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;

    @NotNull(message = "IsPayed is required")
    @Column(name = "is_payed")
    private boolean isPayed;

    @NotNull(message = "TotalPrice is required")
    @Column(name = "total_price")
    private float totalPrice;

    @ManyToOne
    private Seat seat;

    @ManyToOne
    private Showing showing;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
