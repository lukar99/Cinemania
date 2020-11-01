package cinemaapp.cinema.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "seat")
public class Seat {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;

    @NotNull(message = "Number is required")
    @Min(value = 1, message = "Number must be grater than 0")
    @Max(value = 400, message = "Number must be less than 401")
    @Column(name = "number")
    private int number;

    @NotNull(message = "Row is required")
    @Min(value = 1, message = "Row must be grater than 0")
    @Max(value = 10, message = "Row must be less than 11")
    @Column(name = "row")
    private int row;

    @NotNull(message = "column is required")
    @Min(value = 1, message = "column must be grater than 0")
    @Max(value = 20, message = "column must be less than 21")
    @Column(name = "column_seat")
    private int column;

    @ManyToOne
    private Hall hall;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
