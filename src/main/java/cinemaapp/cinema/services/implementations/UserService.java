package cinemaapp.cinema.services.implementations;

import cinemaapp.cinema.models.User;
import cinemaapp.cinema.repositories.UserRepository;
import cinemaapp.cinema.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getByRoles(String role) {
        return userRepository.findByRole(role);
    }

    @Override
    public List<User> getByClubs_Id(long id) {
        return userRepository.findByClub_Id(id);
    }

    @Override
    public Optional<User> getById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        User persistedUser = userRepository.save(user);

        return persistedUser;
    }

    @Override
    public User update(User user) {
        User persistedUser = userRepository.save(user);

        return persistedUser;
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
