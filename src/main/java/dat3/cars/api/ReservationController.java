package dat3.cars.api;

import dat3.cars.service.ReservationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    ReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping("/{memberId}/{carId}/{date}")
    public void makeReservation(@PathVariable String memberId , @PathVariable int carId, @PathVariable String date) {
        //date is assumed to come in as a string formatted like: day-month-year
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate reservationDate = LocalDate.parse(date,formatter);
        reservationService.reserveCar(memberId,carId,reservationDate);
    }
}
