package cinemaapp.cinema.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "user_cinema")
public class User {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;

    @NotNull(message = "First name is required")
    @Size(max = 30, message = "First name must be less than 31 characters")
    @Column(name = "first_name")
    private String first_name;

    @NotNull(message = "Last name is required")
    @Size(max = 30, message = "Last name must be less than 31 characters")
    @Column(name = "last_name")
    private String last_name;

    @NotNull(message = "Email is required")
    @Email(message = "Email not valid")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Username is required")
    @Size(max = 20, message = "Username must be less than 21 characters")
    @Column(name = "username")
    private String username;

    @NotNull(message = "Password is required")
    @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters")
    @Column(name = "password")
    private String password;

    @NotNull(message = "Role is required")
    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "user")
    private Ticket ticket;

    @ManyToMany
    @JoinTable(name = "users_clubs", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "club_id"))
    private Set<Club> club;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public Set<Club> getClub() {
        return club;
    }

    public void setClub(Set<Club> club) {
        this.club = club;
    }
}
