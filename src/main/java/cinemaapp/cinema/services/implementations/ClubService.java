package cinemaapp.cinema.services.implementations;

import cinemaapp.cinema.models.Club;
import cinemaapp.cinema.repositories.ClubRepository;
import cinemaapp.cinema.services.interfaces.IClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService implements IClubService {
    @Autowired
    private ClubRepository clubRepository;

    @Override
    public List<Club> getAll() {
        return clubRepository.findAll();
    }

    @Override
    public Club getById(long id) {
        return clubRepository.findById(id).get();
    }

    @Override
    public List<Club> getByUsers_Username(String username) {
        return clubRepository.findByUsers_Username(username);
    }
}
