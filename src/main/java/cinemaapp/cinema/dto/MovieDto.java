package cinemaapp.cinema.dto;

import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MovieDto {

    @NotNull(message = "Movie title is required")
    @Size(max = 30, message = "Movie title must be less than 30 characters")
    private String title;

    @NotNull(message = "Director is required")
    @Size(max = 80, message = "Director must be less than 80 characters")
    private String director;

    @NotNull(message = "Description is required")
    @Size(max = 800, message = "Description must be less than 800 characters")
    private String description;

    @NotNull(message = "Genre is required")
    @Size(max = 50, message = "Genre must be less than 50 characters")
    private String genre;

    @NotNull(message = "Movie duration is required")
    @Min(value = 1, message = "Duration must be greater than 1 minute")
    @Max(value = 600, message = "Duration must be less than 600 minutes")
    private int duration;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be grater than 0")
    @Max(value = 10, message = "Rating must be less than 11")
    private double rating;

    @NotNull(message = "Release year is required")
    @Min(value = 1600, message = "Year must be grater than 1600")
    @Max(value = 2022, message = "Year must be less than 2022")
    private int year;

    @NotNull(message = "Trailer URL is required")
    @URL(message = "Trailer URL must be URL format")
    private String trailerUrl;

    @NotNull(message = "Image is required")
    private MultipartFile image;


    private boolean isShowing;

    public MovieDto(){}

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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void setShowing(boolean showing) {
        isShowing = showing;
    }
}
