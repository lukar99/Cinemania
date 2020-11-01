package cinemaapp.cinema.repositories;

import cinemaapp.cinema.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByHall_IdOrderByNumberAsc(long hallId);
}
