package cinemaapp.cinema.repositories;

import cinemaapp.cinema.models.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    Cinema findByName(String name);
    List<Cinema> findAllByOrderByNameAsc();
}
