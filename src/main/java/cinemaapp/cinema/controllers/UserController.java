package cinemaapp.cinema.controllers;

import cinemaapp.cinema.config.MyUser;
import cinemaapp.cinema.models.Club;
import cinemaapp.cinema.models.Ticket;
import cinemaapp.cinema.models.User;
import cinemaapp.cinema.services.interfaces.IClubService;
import cinemaapp.cinema.services.interfaces.ITicketService;
import cinemaapp.cinema.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IClubService clubService;

    @Autowired
    private ITicketService ticketService;

    @GetMapping("/login")
    public String loginForm(Model model, @RequestParam(required = false, defaultValue = "false") Boolean error) {
        model.addAttribute("user", new User());

        if (error) {
            model.addAttribute("error", "Username or password is incorrect");
        }

        return "users/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        User user = new User();
        user.setRole("ROLE_USER");

        model.addAttribute("user", user);

        return "users/register";
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView model = new ModelAndView();

        User existingByUsername = userService.getByUsername(user.getUsername());
        if (existingByUsername != null) {
            bindingResult.rejectValue("username", "error.user", "Username already exists");
        }

        User existingByEmail = userService.getByEmail(user.getEmail());
        if (existingByEmail != null) {
            bindingResult.rejectValue("email", "error.user", "Email already exists");
        }

        if (bindingResult.hasErrors()) {
            model.setViewName("users/register");
        }
        else {
            user.setRole("ROLE_USER");

            userService.save(user);

            redirectAttributes.addFlashAttribute("isRegistered", true);

            model.setViewName("users/login");
        }

        return model;
    }

    @GetMapping("/admin/users")
    public String getAllAdmin(Model model)
    {
        List<User> users = userService.getAll();

        model.addAttribute("users", users);

        return "users/list";
    }

    @GetMapping("/admin/users/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        userService.deleteById(id);

        redirectAttributes.addFlashAttribute("isDeleted", true);

        return "redirect:/admin/users";
    }

    @GetMapping("/profile/edit")
    public String edit(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.getByUsername(username);

        model.addAttribute("user", user);

        return "users/edit";
    }

    @PostMapping("/profile/update")
    public String update(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        User existingByUsername = userService.getByUsername(user.getUsername());
        if (existingByUsername != null && existingByUsername.getId() != user.getId()) {
            bindingResult.rejectValue("username", "error.user", "Username already exists");
        }

        User existingByEmail = userService.getByEmail(user.getEmail());
        if (existingByEmail != null && existingByEmail.getId() != user.getId()) {
            bindingResult.rejectValue("email", "error.user", "Email already exists");
        }

        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        else {
            userService.save(user);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            MyUser userDetails = (MyUser) authentication.getPrincipal();
            userDetails.setUsername(user.getUsername());

            redirectAttributes.addFlashAttribute("isUpdated", true);

            return "redirect:/profile/edit";
        }
    }

    @GetMapping("/profile/reservations")
    public String getReservations(Model model, Principal principal) {
        Ticket tickets = ticketService.getByUserUsername(principal.getName());

        model.addAttribute("tickets", tickets);

        return "users/reservations";
    }

    @GetMapping("/profile/clubs")
    public String clubs(Model model, Principal principal) {
        List<Club> clubs = clubService.getByUsers_Username(principal.getName());

        model.addAttribute("clubs", clubs);

        return "users/clubs";
    }
}
