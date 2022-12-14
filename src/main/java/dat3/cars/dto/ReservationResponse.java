package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.cars.entity.Reservation;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {

    int id;
    String memberUsername;
    int carId;
    String brand;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate rentalDate;

    public ReservationResponse(Reservation r) {
        this.id = r.getId();
        this.memberUsername = r.getMember().getUsername();
        this.carId = r.getCar().getId();
        this.brand = r.getCar().getBrand();
    }
}
