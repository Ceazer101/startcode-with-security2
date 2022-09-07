package dat3.cars.dto;

import dat3.cars.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {

    LocalDate date;

    public ReservationResponse(Reservation r) {
        this.date = r.getRentalDate();
    }
}
