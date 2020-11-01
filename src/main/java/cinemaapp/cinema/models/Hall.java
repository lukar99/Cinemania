package cinemaapp.cinema.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "hall")
public class Hall {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;

    @NotNull(message = "Name is required")
    @Length(max = 30, message = "Name must be less than 30 characters")
    @Column(name = "name", unique = true)
    private String name;

    @NotNull(message = "Number of rows is required")
    @Min(value = 1, message = "Number of rows must be grater than 0")
    @Max(value = 10, message = "Number of rows must be less than 11")
    @Column(name = "rows")
    private int rows;

    @NotNull(message = "Number of columns is required")
    @Min(value = 1, message = "Number of columns must be greater than 9")
    @Max(value = 20, message = "Number of columns must be less than 21")
    @Column(name = "columns")
    private int columns;

    @NotNull(message = "Seats number is required")
    @Min(value = 10, message = "Seats number must be greater than 9")
    @Max(value = 200, message = "Seats number must be less than 201")
    @Column(name = "seats_number")
    private int seatsNumber;


    @ManyToOne
    private Cinema cinema;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
    private Set<Showing> showings;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
    private Set<Seat> seats;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }


    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Set<Showing> getShowings() {
        return showings;
    }

    public void setShowings(Set<Showing> showings) {
        this.showings = showings;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }
}
