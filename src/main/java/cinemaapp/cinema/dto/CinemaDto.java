package cinemaapp.cinema.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CinemaDto {

    @Id
    private long id;

    @NotNull(message = "Name is required")
    @Size(max = 30, message = "Name must be less than 30 characters")
    private String name;

    @NotNull(message = "Phone number is required")
    @Size(max = 20, min = 9, message = "Phone number must be between 9 and 20 characters")
    private String phone_number;

    @NotNull(message = "Address is required")
    @Size(max = 50, message = "Address must be less than 50 characters")
    private String address;

    @NotNull(message = "Image is required")
    private MultipartFile image;

    @NotNull(message = "City is required")
    private String city;

    public CinemaDto(){}

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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
