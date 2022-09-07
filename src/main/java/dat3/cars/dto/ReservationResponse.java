package dat3.cars.dto;

import dat3.cars.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {

    int id;
    LocalDateTime reservationDate;
    LocalDate rentalDate;

    public ReservationResponse(Reservation r) {
        this.id = r.getId();
        this.reservationDate = r.getReservationDate();
        this.rentalDate = r.getRentalDate();
    }
}
