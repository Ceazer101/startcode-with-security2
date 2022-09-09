package dat3.cars.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//------------------
@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    Member member1;

    @ManyToOne
    Car car1;

    LocalDate rentalDate;

    public Rental(Member member, Car car, LocalDate rentalDate) {
        this.member1  =member;
        this.car1 = car;
        car.addRental(this);
        member.addRental(this);
        this.rentalDate = rentalDate;
    }
}
