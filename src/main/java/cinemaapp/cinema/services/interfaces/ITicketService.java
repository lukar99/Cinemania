package cinemaapp.cinema.services.interfaces;

import cinemaapp.cinema.models.Ticket;

import java.util.List;

public interface ITicketService {
    List<Ticket> getAll();
    List<Ticket> getByShowing_Id(long id);
    Ticket getByUserUsername(String user);
    List<Ticket> getByUserIdAndShowingIdAndSeatId(long userId, long showingId, long seatId);
    Ticket getById(long id);
    List<Ticket> getByIsPayed(boolean isPayed);
    Ticket save(Ticket ticket);
    void deleteById(long id);
}
