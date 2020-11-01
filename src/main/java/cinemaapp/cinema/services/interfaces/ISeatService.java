package cinemaapp.cinema.services.interfaces;

import cinemaapp.cinema.models.Seat;

import java.util.List;

public interface ISeatService {
    Seat getById(long id);
    List<Seat> getAll();
    List<Seat> getByHall_IdOrderByNumberAsc(long hallId);
    Seat save(Seat seat);
}
