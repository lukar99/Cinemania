package cinemaapp.cinema.services.implementations;

import cinemaapp.cinema.models.Ticket;
import cinemaapp.cinema.repositories.TicketRepository;
import cinemaapp.cinema.services.interfaces.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements ITicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getByShowing_Id(long id) {
        return ticketRepository.findByShowing_Id(id);
    }



    @Override
    public Ticket getByUserUsername(String user) {
        return ticketRepository.findByUser_Username(user);
    }

    @Override
    public List<Ticket> getByUserIdAndShowingIdAndSeatId(long userId, long showingId, long seatId) {
        return ticketRepository.findByUser_IdAndShowing_IdAndSeat_Id(userId, showingId, seatId);
    }


    @Override
    public Ticket getById(long id) {
        return ticketRepository.findById(id).get();
    }

    @Override
    public List<Ticket> getByIsPayed(boolean isPayed) {
        return ticketRepository.findByIsPayed(isPayed);
    }

    @Override
    public Ticket save(Ticket ticket) {
        Ticket persistedTicket = ticketRepository.save(ticket);

        return persistedTicket;
    }

    @Override
    public void deleteById(long id) {
        this.ticketRepository.deleteById(id);
    }
}
