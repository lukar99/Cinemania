package cinemaapp.cinema.controllers;

import cinemaapp.cinema.dto.MovieDto;
import cinemaapp.cinema.dto.SearchDto;
import cinemaapp.cinema.models.Cinema;
import cinemaapp.cinema.models.Movie;
import cinemaapp.cinema.models.Showing;
import cinemaapp.cinema.services.interfaces.ICinemaService;
import cinemaapp.cinema.services.interfaces.IMovieService;
import cinemaapp.cinema.services.interfaces.IShowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @Autowired
    private ICinemaService cinemaService;

    @Autowired
    private IShowingService showingService;

    @GetMapping("/movies")
    private String getAll(Model model)
    {
        List<Movie> movies = movieService.getAll();

        model.addAttribute("movies", movies);

        return "movies/index";
    }

    @GetMapping("/movies/{id}")
    public String get(Model model, @PathVariable Long id) {
        Optional<Movie> movie = movieService.getById(id);
        List<Cinema> cinemas = cinemaService.getAll();
       // List<Showing> showings = showingService.getAll();
        SearchDto search = new SearchDto();
        search.setMovieId(id);

        model.addAttribute("movie", movie.get());
       // model.addAttribute("showings", showings);
        model.addAttribute("cinemas", cinemas);
        model.addAttribute("search", search);
        model.addAttribute("minDate", LocalDate.now().toString());

        return "movies/details";
    }

    @GetMapping("/admin/movies")
    public String getAllAdmin(Model model)
    {
        List<Movie> movies = movieService.getAll();

        model.addAttribute("movies", movies);

        return "movies/list";
    }

    @GetMapping("/admin/movies/create")
    public String create(Model model) {
        model.addAttribute("movieDto", new MovieDto());

        return "movies/create";
    }

    @PostMapping("/admin/movies/save")
    public String store(@Valid MovieDto movieDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        Movie enteredMovie = movieService.getByTitle(movieDto.getTitle());

        if (enteredMovie != null) {
            bindingResult.rejectValue("title", "movie.title", "Title already exists");
        }

        if (bindingResult.hasErrors()) {
            return "movies/create";
        }
        else {
            Movie movie = new Movie();
            movie.setTitle(movieDto.getTitle());
            movie.setDescription(movieDto.getDescription());
            movie.setDirector(movieDto.getDirector());
            movie.setDuration(movieDto.getDuration());
            movie.setGenre(movieDto.getGenre());
            movie.setRating(movieDto.getRating());
            movie.setTrailerUrl(movieDto.getTrailerUrl());
            movie.setYear(movieDto.getYear());
            movie.setShowing(true);

            try {
                String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/img";
                Path path = Paths.get(uploadDirectory, movieDto.getImage().getOriginalFilename());
                byte[] bytes = movieDto.getImage().getBytes();
                Files.write(path, bytes);
                String shortUrl = "/img/" +movieDto.getImage().getOriginalFilename();

                movie.setImage(shortUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }

            movieService.save(movie);

            redirectAttributes.addFlashAttribute("isCreated", true);

            return "redirect:/admin/movies";
        }
    }

    @GetMapping("/admin/movies/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        movieService.deleteById(id);

        redirectAttributes.addFlashAttribute("isDeleted", true);

        return "redirect:/admin/movies";
    }

    @GetMapping("/admin/movies/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        Optional<Movie> movie = movieService.getById(id);

        model.addAttribute("movie", movie.get());

        return "movies/edit";
    }

    @PostMapping("/admin/movies/update")
    public String update(@Valid Movie movie, @RequestParam MultipartFile imageFile, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        Movie existingMovie = movieService.getByTitle(movie.getTitle());

        if (existingMovie != null && existingMovie.getId() != movie.getId()) {
            bindingResult.rejectValue("title", "movie.title", "Title already exists");
        }

        if (bindingResult.hasErrors()) {
            return "movies/edit";
        }
        else {

            if (!imageFile.isEmpty()) {
                try {
                    String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/img";
                    Path path = Paths.get(uploadDirectory, imageFile.getOriginalFilename());
                    byte[] bytes = imageFile.getBytes();
                    Files.write(path, bytes);
                    String shortUrl = "/img/" +imageFile.getOriginalFilename();

                    movie.setImage(shortUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            movie.setShowing(true);
            movieService.update(movie);

            redirectAttributes.addFlashAttribute("isUpdated", true);

            return "redirect:/admin/movies/edit/" +movie.getId();
        }
    }


    @PostMapping("/movies/showings/search")
    public String search(SearchDto search, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "redirect:/movies/" +search.getMovieId()  +"#here";
        }

        List<Showing> showings = showingService.getByCinemaIdAndMovieIdAndMovieIsShowing(search.getCinemaId(), search.getMovieId());

        if (showings.size() == 0) {
            redirectAttributes.addFlashAttribute("noShowings", showings);
        }

        redirectAttributes.addFlashAttribute("showings", showings);

        return "redirect:/movies/" +search.getMovieId() +"#here";
    }

    @GetMapping("/movies/{id}/showings")
    public String allShowings(@PathVariable long id, Model model) {
        List<Showing> showings = showingService.getByMovie_IdAndMovie_IsShowingAndDateGreaterThanEqualOrderByDateAsc(id, LocalDate.now());
        Movie movie = movieService.getById(id).get();

        if (showings.isEmpty()) {
            model.addAttribute("noShowings", true);
        }

        model.addAttribute("showings", showings);
        model.addAttribute("movie", movie);

        return "movies/showings";
    }
}
