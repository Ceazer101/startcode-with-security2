package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.cars.entity.Rental;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentalResponse {

    int id;
    String memberUsername;
    int carId;
    double pricePrDay;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDateTime rentalDate;

    public RentalResponse(Rental r) {
        this.id = r.getId();
        this.memberUsername = r.getMember1().getUsername();
        this.carId = r.getCar1().getId();
        this.pricePrDay = r.getCar1().getPricePrDay();
    }
}
