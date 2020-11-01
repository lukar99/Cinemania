package cinemaapp.cinema.controllers;

import cinemaapp.cinema.models.*;
import cinemaapp.cinema.services.implementations.CinemaService;
import cinemaapp.cinema.services.implementations.MovieService;
import cinemaapp.cinema.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private IHallService hallService;

    @Autowired
    private ISeatService seatService;

    @Autowired
    private IShowingService showingService;

    @Autowired
    private ITicketService ticketService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IClubService clubService;

    @GetMapping("/Cinema")
    public String home(Model model) {
        List<Movie> movies = movieService.getAll();
        List<Cinema> cinemas = cinemaService.getAll();

        model.addAttribute("movies", movies.subList(0, 2));
        model.addAttribute("cinemas", cinemas.subList(0, 3));

        return "home/index";
    }

    @GetMapping("/manager")
    public String manager(Model model) {
        List<Cinema> cinemas = cinemaService.getAll();
        List<Hall> halls = hallService.getAll();
        List<Seat> seats = seatService.getAll();
        List<Movie> movies = movieService.getAll();
        List<Movie> showingMovies = movieService.getByIsShowingTrue();
        List<Showing> showings = showingService.getAll();
        List<Showing> activeShowings = showingService.getByDateGreaterThanEqual();
        List<User> users = userService.getByRoles("ROLE_USER");
        List<Club> clubs = clubService.getAll();
        List<User> admins = userService.getByRoles("ROLE_ADMIN");
        List<User> managers = userService.getByRoles("ROLE_MANAGER");
        List<User> premiumMembers = userService.getByClubs_Id(1);
        List<User> standardMembers = userService.getByClubs_Id(3);
        List<User> bonusCards = userService.getByClubs_Id(2);
        List<Ticket> soldTickets = ticketService.getByIsPayed(true);

        float totalIncome = 0;
        float thisMonth = 0;
        float lastSevenDays = 0;
        float today = 0;

        for (Ticket res : soldTickets) {
            totalIncome += res.getTotalPrice();

        }

        model.addAttribute("cinemas", cinemas.size());
        model.addAttribute("halls", halls.size());
        model.addAttribute("seats", seats.size());
        model.addAttribute("movies", movies.size());
        model.addAttribute("showingMovies", showingMovies.size());
        model.addAttribute("showings", showings.size());
        model.addAttribute("activeShowings", activeShowings.size());
        model.addAttribute("soldReservations", soldTickets.size());
        model.addAttribute("soldTickets", soldTickets.size());
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("thisMonth", thisMonth);
        model.addAttribute("lastSevenDays", lastSevenDays);
        model.addAttribute("today", today);
        model.addAttribute("users", users.size());
        model.addAttribute("clubs", clubs.size());
        model.addAttribute("admins", admins.size());
        model.addAttribute("managers", managers.size());
        model.addAttribute("premiumMembers", premiumMembers.size());
        model.addAttribute("standardMembers", standardMembers.size());
        model.addAttribute("bonusCards", bonusCards.size());

        return "home/manager";
    }
}
