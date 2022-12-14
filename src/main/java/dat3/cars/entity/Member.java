package dat3.cars.entity;

import dat3.security.entity.UserWithRoles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends UserWithRoles {

    @Column(length = 30)
    String firstName;

    @Column(length = 50)
    String lastName;

    @Column(length = 50)
    String street;

    @Column(length = 50)
    String city;

    @Column(length = 50)
    String zip;

    boolean approved;
    int ranking;

    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "member1")
    private List<Rental> rentals = new ArrayList<>();

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    public void addRental(Rental rental){
        rentals.add(rental);
    }

    public Member(String user, String password, String email, String firstName, String lastName, String street, String city, String zip) {
        super(user, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }
}
