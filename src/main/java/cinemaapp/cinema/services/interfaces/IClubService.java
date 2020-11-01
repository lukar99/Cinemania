package cinemaapp.cinema.services.interfaces;

import cinemaapp.cinema.models.Club;

import java.util.List;

public interface IClubService {
    List<Club> getAll();
    Club getById(long id);
    List<Club> getByUsers_Username(String username);
}
