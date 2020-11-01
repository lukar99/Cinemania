package cinemaapp.cinema.controllers;

import cinemaapp.cinema.dto.CinemaDto;
import cinemaapp.cinema.dto.SearchDto;
import cinemaapp.cinema.models.Cinema;
import cinemaapp.cinema.models.Hall;
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
import java.util.Set;

@Controller
public class CinemaController {

    @Autowired
    private ICinemaService cinemaService;

    @Autowired
    private IMovieService movieService;

    @Autowired
    private IShowingService showingService;

    @GetMapping("/cinemas")
    private String getAll(Model model)
    {
        List<Cinema> cinemas = cinemaService.getAll();

        model.addAttribute("cinemas",cinemas);

        return "cinemas/index";
    }

    @GetMapping("/admin/cinemas")
    public String getAllAdmin(Model model)
    {
        List<Cinema> cinemas = cinemaService.getAll();

        model.addAttribute("cinemas", cinemas);

        return "cinemas/list";
    }

    @GetMapping("/admin/cinemas/create")
    public String create(Model model) {

        model.addAttribute("cinemaDto", new CinemaDto());

        return "cinemas/create";
    }

    @PostMapping("/admin/cinemas/save")
    public String store(@Valid CinemaDto cinemaDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        Cinema existingCinema = cinemaService.getByName(cinemaDto.getName());

        if (existingCinema != null) {


            bindingResult.rejectValue("name", "cinema.name", "Name already exists");
        }

        if (bindingResult.hasErrors()) {
            return "cinemas/create";
        }
        else {
            Cinema cinema = new Cinema();
            cinema.setName(cinemaDto.getName());
            cinema.setAddress(cinemaDto.getAddress());
            cinema.setPhone_number(cinemaDto.getPhone_number());
            cinema.setCity(cinemaDto.getCity());


            try {
                String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/img";
                Path path = Paths.get(uploadDirectory, cinemaDto.getImage().getOriginalFilename());
                byte[] bytes = cinemaDto.getImage().getBytes();
                Files.write(path, bytes);
                String shortUrl = "/img/" +cinemaDto.getImage().getOriginalFilename();

                cinema.setImage(shortUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }

            cinemaService.save(cinema);

            redirectAttributes.addFlashAttribute("isCreated", true);

            return "redirect:/admin/cinemas";
        }
    }

    @GetMapping("/admin/cinemas/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        Optional<Cinema> cinema = cinemaService.getById(id);

        model.addAttribute("cinema", cinema.get());

        return "cinemas/edit";
    }

    @PostMapping("/admin/cinemas/update")
    public String update(@Valid Cinema cinema, @RequestParam MultipartFile imageFile, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        Cinema existingCinema = cinemaService.getByName(cinema.getName());

        if (existingCinema != null && existingCinema.getId() != cinema.getId()) {


            bindingResult.rejectValue("name", "cinema.name", "Name already exists");
        }

        if (bindingResult.hasErrors()) {
            return "cinemas/edit";
        }
        else {
            if (!imageFile.isEmpty()) {
                try {
                    String uploadDirectory = System.getProperty("user.dir") + "/src/main/uploads/images";
                    Path path = Paths.get(uploadDirectory, imageFile.getOriginalFilename());
                    byte[] bytes = imageFile.getBytes();
                    Files.write(path, bytes);
                    String shortUrl = "/uploads/images/" +imageFile.getOriginalFilename();

                    cinema.setImage(shortUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            cinemaService.update(cinema);

            redirectAttributes.addFlashAttribute("isUpdated", true);

            return "redirect:/admin/cinemas/edit/" +cinema.getId();
        }
    }

    @GetMapping("/admin/cinemas/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        cinemaService.deleteById(id);

        redirectAttributes.addFlashAttribute("isDeleted", true);

        return "redirect:/admin/cinemas";
    }

    @GetMapping("/cinemas/{id}/showings")
    public String showings(@PathVariable long id, Model model) {
        List<Showing> showings = showingService.getByHall_Cinema_IdAndDateGreaterThanEqualOrderByDateAsc(id, LocalDate.now());
        Cinema cinema = cinemaService.getById(id).get();

        if (showings.isEmpty()) {
            model.addAttribute("noShowings", true);
        }

        model.addAttribute("showings", showings);
        model.addAttribute("cinema", cinema);

        return "cinemas/showings";
    }

    @GetMapping("/cinemas/{id}/halls")
    public String halls(@PathVariable long id, Model model) {
        Cinema cinema = cinemaService.getById(id).get();
        Set<Hall> halls = cinema.getHalls();

        if (halls.isEmpty()) {
            model.addAttribute("noHalls", true);
        }

        model.addAttribute("halls", halls);
        model.addAttribute("cinema", cinema);

        return "cinemas/halls";
    }

    @GetMapping("/admin/cinemas/{id}/showings")
    public String adminShowings(@PathVariable long id, Model model) {
        List<Showing> showings = showingService.getByHall_Cinema_IdOrderByDateDesc(id);
        Cinema cinema = cinemaService.getById(id).get();

        if (showings.isEmpty()) {
            model.addAttribute("noShowings", true);
        }

        model.addAttribute("showings", showings);
        model.addAttribute("cinema", cinema);

        return "cinemas/admin-showings";
    }

    @GetMapping("/cinemas/{id}")
    public String get(Model model, @PathVariable Long id) {
        Optional<Cinema> cinema = cinemaService.getById(id);
        List<Movie> movies = movieService.getAll();
        SearchDto search = new SearchDto();
        search.setCinemaId(id);

        model.addAttribute("cinema", cinema.get());
        model.addAttribute("movies", movies);
        model.addAttribute("search", search);
        model.addAttribute("minDate", LocalDate.now().toString());

        return "cinemas/details";
    }

    @PostMapping("/cinemas/showings/search")
    public String search(SearchDto search, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "redirect:/cinemas/" +search.getCinemaId()  +"#here";
        }

        List<Showing> showings = showingService.getByCinemaIdAndMovieIdAndDateAndMovieIsShowing(search.getCinemaId(), search.getMovieId(), LocalDate.parse(search.getDate()));

        if (showings.size() == 0) {
            redirectAttributes.addFlashAttribute("noShowings", showings);
        }

        redirectAttributes.addFlashAttribute("showings", showings);

        return "redirect:/cinemas/" +search.getCinemaId() +"#here";
    }


}
