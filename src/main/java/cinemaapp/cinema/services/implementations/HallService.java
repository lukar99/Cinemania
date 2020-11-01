package cinemaapp.cinema.services.implementations;

import cinemaapp.cinema.models.Hall;
import cinemaapp.cinema.repositories.HallRepository;
import cinemaapp.cinema.services.interfaces.IHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HallService implements IHallService {

    @Autowired
    private HallRepository hallRepository;


    @Override
    public List<Hall> getAll() {
        return this.hallRepository.findAll();
    }

    @Override
    public Hall save(Hall hall) {
        Hall persistedHall = this.hallRepository.save(hall);

        return persistedHall;
    }

    @Override
    public Hall getByName(String name) {
        return hallRepository.findByName(name);
    }

    @Override
    public Optional<Hall> getById(long id) {
        return this.hallRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        this.hallRepository.deleteById(id);
    }

    @Override
    public Hall update(Hall hall) {
        Hall persistedHall = this.hallRepository.save(hall);

        return persistedHall;
    }
}
