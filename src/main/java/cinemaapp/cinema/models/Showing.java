package cinemaapp.cinema.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "showing")
public class Showing {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;

    @NotNull(message = "Time is required")
    @Column(name = "time")
    private LocalTime time;

    @NotNull(message = "Date is required")
    @Column(name = "date")
    private LocalDate date;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Max(value = 10000, message = "Price must be less than 10001")
    @Column(name = "price")
    private float price;

    @NotNull(message = "ShowingType is required")
    @Column(name = "showing_type")
    private String showingType;

    @ManyToOne
    private Hall hall;

    @ManyToOne
    private Movie movie;

    @OneToMany(mappedBy = "showing", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getShowingType() {
        return showingType;
    }

    public void setShowingType(String showingType) {
        this.showingType = showingType;
    }
}
