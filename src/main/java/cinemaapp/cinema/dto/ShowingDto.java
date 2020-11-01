package cinemaapp.cinema.dto;

import cinemaapp.cinema.models.Hall;
import cinemaapp.cinema.models.Movie;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class ShowingDto {
    private long id;

    @NotNull(message = "Time is required")
    private String time;

    @NotNull(message = "Date is required")
    private String date;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Max(value = 10000, message = "Price must be less than 10001")
    private float price;

    @NotNull(message = "ShowingType is required")
    private String showingType;

    private Hall hall;

    private Movie movie;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getShowingType() {
        return showingType;
    }

    public void setShowingType(String showingType) {
        this.showingType = showingType;
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
}
