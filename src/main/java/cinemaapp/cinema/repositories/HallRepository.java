package cinemaapp.cinema.repositories;

import cinemaapp.cinema.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall, Long> {
    Hall findByName(String name);
}
