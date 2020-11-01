package cinemaapp.cinema.models;

import javax.persistence.*;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "cinema")
public class Cinema {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;

    @NotNull(message = "Name is required")
    @Size(max = 30, message = "Name must be less than 30 characters")
    @Column(name = "name", unique = true)
    private String name;

    @NotNull(message = "Phone number is required")
    @Size(max = 20, min = 9, message = "Phone number must be between 9 and 20 characters")
    @Column(name = "phone_number")
    private String phone_number;

    @NotNull(message = "Address is required")
    @Size(max = 50, message = "Address must be less than 50 characters")
    @Column(name = "address")
    private String address;

    @NotNull(message = "Image is required")
    @Column(name = "image")
    private String image;

    @NotNull(message = "City is required")
    @Column(name= "city")
    private String city;



    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL)
    private Set<Hall> halls;

    public Set<Hall> getHalls() {
        return halls;
    }

    public void setHalls(Set<Hall> halls) {
        this.halls = halls;
    }

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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
