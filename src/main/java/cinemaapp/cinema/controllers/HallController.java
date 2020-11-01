package cinemaapp.cinema.controllers;

import cinemaapp.cinema.models.Cinema;
import cinemaapp.cinema.models.Hall;
import cinemaapp.cinema.models.Seat;
import cinemaapp.cinema.services.interfaces.ICinemaService;
import cinemaapp.cinema.services.interfaces.IHallService;
import cinemaapp.cinema.services.interfaces.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class HallController {

    @Autowired
    private IHallService hallService;

    @Autowired
    private ICinemaService cinemaService;

    @Autowired
    private ISeatService seatService;

    @GetMapping("/admin/halls")
    public String getAllAdmin(Model model)
    {
        List<Hall> halls = hallService.getAll();

        model.addAttribute("halls", halls);

        return "halls/list";
    }

    @GetMapping("/admin/halls/create")
    public String create(Model model) {
        List<Cinema> cinemas = cinemaService.getAll();
        Hall hall = new Hall();
        hall.setRows(5);
        hall.setColumns(10);

        model.addAttribute("hall", hall);
        model.addAttribute("cinemas", cinemas);

        return "halls/create";
    }

    @PostMapping("/admin/halls/save")
    public String store(@Valid Hall hall, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        Hall existingHall = hallService.getByName(hall.getName());

        if (existingHall != null) {
            List<Cinema> cinemas = cinemaService.getAll();

            model.addAttribute("cinemas", cinemas);

            bindingResult.rejectValue("name", "hall.name", "Name already exists");
        }

        if (bindingResult.hasErrors()) {
            return "halls/create";
        }
        else {
            int seats = hall.getSeatsNumber();
            int columns = seats > 20 ? 10 : 5;
            int rows = seats % columns == 0 ? seats / columns : seats / columns + 1;

            hall.setColumns(columns);
            hall.setRows(rows);
            Hall newHall = hallService.save(hall);

            for (int i = 1; i <= seats; i++) {
                Seat seat = new Seat();
                seat.setHall(newHall);
                seat.setNumber(i);
                seat.setRow((i + columns - 1) / columns);
                if(i<=columns){
                    seat.setColumn(i);
                }
                else{
                    seat.setColumn(i-(columns*(seat.getRow()-1)));
                }

                seatService.save(seat);
            }

            redirectAttributes.addFlashAttribute("isCreated", true);

            return "redirect:/admin/halls";
        }
    }

    @GetMapping("/admin/halls/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        Optional<Hall> hall = hallService.getById(id);
        List<Cinema> cinemas = cinemaService.getAll();

        model.addAttribute("hall", hall.get());
        model.addAttribute("cinemas", cinemas);

        return "halls/edit";
    }

    @PostMapping("/admin/halls/update")
    public String update(@Valid Hall hall, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        Hall existingHall = hallService.getByName(hall.getName());

        if (existingHall != null && existingHall.getId() != hall.getId()) {
            List<Cinema> cinemas = cinemaService.getAll();

            model.addAttribute("cinemas", cinemas);

            bindingResult.rejectValue("name", "hall.name", "Name already exists");
        }

        if (bindingResult.hasErrors()) {
            return "halls/edit";
        }
        else {
            int seats = hall.getSeatsNumber();
            int columns = seats > 20 ? 10 : 5;
            int rows = seats % columns == 0 ? seats / columns : seats / columns + 1;

            hall.setColumns(columns);
            hall.setRows(rows);

            hallService.update(hall);

            redirectAttributes.addFlashAttribute("isUpdated", true);

            return "redirect:/admin/halls/edit/" +hall.getId();
        }
    }

    @GetMapping("/admin/halls/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        hallService.deleteById(id);

        redirectAttributes.addFlashAttribute("isDeleted", true);

        return "redirect:/admin/halls";
    }


}
