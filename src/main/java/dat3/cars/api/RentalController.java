package dat3.cars.api;

import dat3.cars.dto.RentalResponse;
import dat3.cars.dto.ReservationResponse;
import dat3.cars.service.RentalService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api/rentals")
public class RentalController {

    RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/{memberId}/{carId}/{date}")
    public void makeRental(@PathVariable String memberId , @PathVariable int carId, @PathVariable String date) {
        //date is assumed to come in as a string formatted like: day-month-year
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate rentalDate = LocalDate.parse(date,formatter);
        rentalService.rentCar(memberId,carId,rentalDate);
    }

    @GetMapping
    public List<RentalResponse> getRentals(){
        return rentalService.getRentals();
    }
}
