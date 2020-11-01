package cinemaapp.cinema.services.implementations;

import cinemaapp.cinema.models.Cinema;
import cinemaapp.cinema.repositories.CinemaRepository;
import cinemaapp.cinema.services.interfaces.ICinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService implements ICinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Override
    public List<Cinema> getAll() {
        return this.cinemaRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Cinema save(Cinema cinema) {
        Cinema persistedCinema = this.cinemaRepository.save(cinema);

        return persistedCinema;
    }

    @Override
    public Optional<Cinema> getById(long id) {
        return this.cinemaRepository.findById(id);
    }

    @Override
    public Cinema getByName(String name) {
        return cinemaRepository.findByName(name);
    }

    @Override
    public void deleteById(long id) {
        this.cinemaRepository.deleteById(id);
    }

    @Override
    public Cinema update(Cinema cinema) {
        Cinema persistedCinema = this.cinemaRepository.save(cinema);

        return persistedCinema;
    }
}
