package cinemaapp.cinema.repositories;

import cinemaapp.cinema.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByShowing_Id(long id);
    List<Ticket> findByUser_IdAndShowing_IdAndSeat_Id(long userId, long showingId, long seatId);
    List<Ticket> findByIsPayed(boolean isPayed);
    Ticket findByUser_Username(String username);
}
