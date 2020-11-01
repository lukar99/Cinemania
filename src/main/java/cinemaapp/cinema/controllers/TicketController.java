package cinemaapp.cinema.controllers;

import cinemaapp.cinema.config.MyUser;
import cinemaapp.cinema.dto.SeatDto;
import cinemaapp.cinema.models.*;
import cinemaapp.cinema.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TicketController {
    @Autowired
    private IShowingService showingService;

    @Autowired
    private ISeatService seatService;

    @Autowired
    private ITicketService ticketService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IClubService clubService;

    @GetMapping("/showings/{id}/reservations/create")
    public String create(@PathVariable long id, Model model){
        Ticket ticket = new Ticket();
        Showing showing = showingService.getById(id).get();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUser user = (MyUser) auth.getPrincipal();

        User newUser = userService.getByUsername(user.getUsername());

        Club club = clubService.getById(3);
        if (newUser.getClub().contains(club)) {
            ticket.setTotalPrice(showing.getPrice() * 0.7f);
        } else {
            ticket.setTotalPrice(showing.getPrice());
        }

        ticket.setShowing(showing);
        ticket.setPayed(true);
        ticket.setUser(newUser);

        List<Seat> seats = seatService.getByHall_IdOrderByNumberAsc(showing.getHall().getId());
        List<SeatDto> seatsDto = new ArrayList<SeatDto>();

        for (Seat seat : seats) {
            SeatDto seatDto = new SeatDto();
            seatDto.setId(seat.getId());
            seatDto.setNumber(seat.getNumber());
            seatDto.setRow(seat.getRow());
            seatDto.setColumn(seat.getColumn());
            seatDto.setReserved(true);

            seatsDto.add(seatDto);
        }
        model.addAttribute("ticket", ticket);
        model.addAttribute("seats", seatsDto);
        return "tickets/create";
    }

    @PostMapping("/ticket/reserve")
    public String store(@Valid Ticket ticket, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){

            Ticket newTicket = new Ticket();
            newTicket.setUser(ticket.getUser());
            newTicket.setPayed(ticket.isPayed());
            newTicket.setTotalPrice(ticket.getTotalPrice());
            newTicket.setShowing(ticket.getShowing());
            newTicket.setSeat(ticket.getSeat());


            ticketService.save(newTicket);

        return "redirect:/movies";
    }


    @GetMapping("/admin/reservations")
    public String getAll(Model model)
    {
        List<Ticket> tickets = ticketService.getAll();

        model.addAttribute("tickets", tickets);

        return "tickets/list";
    }


    @GetMapping("/admin/reservations/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        ticketService.deleteById(id);

        redirectAttributes.addFlashAttribute("isDeleted", true);

        return "redirect:/admin/reservations";
    }

//    @GetMapping("/showings/{id}/reservations/create")
//    public String create(@PathVariable long id, Model model) {
//        Ticket ticket = new Ticket();
//        ticket.setTotalPrice(10);
//
//        Showing showing = showingService.getById(id).get();
//
//        List<Seat> seats = seatService.getByHall_IdOrderByNumberAsc(showing.getHall().getId());
//        List<SeatDto> seatsDto = new ArrayList<SeatDto>();
//
//        for (Seat seat : seats) {
//            SeatDto seatDto = new SeatDto();
//            seatDto.setId(seat.getId());
//            seatDto.setNumber(seat.getNumber());
//            seatDto.setRow(seat.getRow());
//            seatDto.setColumn(seat.getColumn());
//            seatDto.setReserved(false);
//
//            seatsDto.add(seatDto);
//        }
//
//        List<Ticket> tickets = ticketService.getByShowing_Id(showing.getId());
//        Ticket ticketReservation = ticketService.getByUserIdAndShowingIdAndSeatId(ticket.getUser().getId(), );
//
//
//            for (SeatDto seat : seatsDto) {
//                if (seat.getId() == ticketReservation.getSeat().getId()) {
//                    seat.setReserved(true);
//                }
//            }
//
//        model.addAttribute("reservation", ticket);
//        model.addAttribute("showing", showing);
//        model.addAttribute("seats", seatsDto);
//
//        return "reservations/create";
//    }


}
