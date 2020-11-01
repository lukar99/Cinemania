package cinemaapp.cinema.models;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "club")
public class Club {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;

    @NotNull(message = "Name is required")
    @Size(max = 30, message = "Name must be less than 30 characters")
    @Column(name = "name", unique = true)
    private String name;

    @NotNull(message = "Description is required")
    @Size(max = 450, message = "Description must be less than 450 characters")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Image is required")
    @Column(name = "image")
    private String image;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @Column(name = "price")
    private float price;

    @ManyToMany(mappedBy = "club")
    private Set<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
