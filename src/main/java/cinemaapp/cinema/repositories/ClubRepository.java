package cinemaapp.cinema.repositories;

import cinemaapp.cinema.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findByUsers_Username(String username);
}
