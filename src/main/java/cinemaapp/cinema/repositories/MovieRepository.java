package cinemaapp.cinema.repositories;

import cinemaapp.cinema.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByShowings_DateGreaterThanEqual(LocalDate date);
    List<Movie> findByIsShowingTrue();
    List<Movie> findAllByOrderByTitleAsc();
    Movie findByTitle(String title);
}
