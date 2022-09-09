package dat3.cars.service;

import dat3.cars.dto.RentalResponse;
import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Rental;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.RentalRepository;
import dat3.cars.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {

    RentalRepository rentalRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;

    public RentalService(RentalRepository rentalRepository, MemberRepository memberRepository,
                              CarRepository carRepository) {
        this.rentalRepository = rentalRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
    }

    public void rentCar(String username, int carId, LocalDate date){

        Member member = memberRepository.findById(username)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not found"));
        Car car = carRepository.findById(carId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car not found"));
        if(rentalRepository.existsByCar1_IdAndRentalDate(carId, date)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car already reserved");
        }

        Rental rental = new Rental(member, car, date);
        rentalRepository.save(rental);
    }

    public List<RentalResponse> getRentals() {
        List<Rental> rentals = rentalRepository.findAll();

        List<RentalResponse> response = rentals.stream().map(rental -> new RentalResponse(rental)).collect(Collectors.toList());

        return response;
    }
}
