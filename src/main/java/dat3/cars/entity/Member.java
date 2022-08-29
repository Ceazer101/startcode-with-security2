package dat3.cars.entity;

import dat3.security.entity.UserWithRoles;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Member extends UserWithRoles {

    String firstName;
    String lastName;
    String street;
    String city;
    String zip;
    boolean approved;
    String ranking;

    public Member(String user, String password, String email,
                  String firstName, String lastName, String street,
                  String city, String zip, boolean approved, String ranking) {

        super(user, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.approved = approved;
        this.ranking = ranking;
    }

    public Member() {}

    @Override
    public String toString() {
        return "Member{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", approved=" + approved +
                ", ranking='" + ranking + '\'' +
                '}';
    }
}
