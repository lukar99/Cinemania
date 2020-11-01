package cinemaapp.cinema.models;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;

    @NotNull(message = "Movie title is required")
    @Size(max = 30, message = "Movie title must be less than 30 characters")
    @Column(name = "title")
    private String title;

    @NotNull(message = "Director is required")
    @Size(max = 80, message = "Director must be less than 80 characters")
    @Column(name = "director")
    private String director;

    @NotNull(message = "Description is required")
    @Size(max = 800, message = "Description must be less than 800 characters")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Genre is required")
    @Size(max = 50, message = "Genre must be less than 50 characters")
    @Column(name = "genre")
    private String genre;

    @NotNull(message = "Movie duration is required")
    @Min(value = 1, message = "Duration must be greater than 1 minute")
    @Max(value = 600, message = "Duration must be less than 600 minutes")
    @Column(name = "duration")
    private int duration;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be grater than 0")
    @Max(value = 10, message = "Rating must be less than 11")
    @Column(name = "rating")
    private double rating;

    @NotNull(message = "Release year is required")
    @Min(value = 1600, message = "Year must be grater than 1600")
    @Max(value = 2022, message = "Year must be less than 2022")
    @Column(name = "year")
    private int year;

    @NotNull(message = "Trailer URL is required")
    @URL(message = "Trailer URL must be URL format")
    @Column(name = "trailer_url")
    private String trailerUrl;

    @NotNull(message = "Image is required")
    @Column(name = "image")
    private String image;


    @Column(name = "is_showing")
    private boolean isShowing;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<Showing> showings;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public boolean isShowing() {
        return isShowing;
    }

    public void setShowing(boolean showing) {
        isShowing = showing;
    }

    public Set<Showing> getShowings() {
        return showings;
    }

    public void setShowings(Set<Showing> showings) {
        this.showings = showings;
    }
}
