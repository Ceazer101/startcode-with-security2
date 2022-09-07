package dat3.cars.service;

import dat3.cars.dto.MemberResponse;
import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;

    public ReservationService(ReservationRepository reservationRepository, MemberRepository memberRepository,
                              CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
    }

    public void reserveCar(String username, int carId, LocalDate date){

        Member member = memberRepository.findById(username)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not found"));
        Car car = carRepository.findById(carId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car not found"));
        if(reservationRepository.existsByCar_IdAndRentalDate(carId, date)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car already reserved");
        }

        Reservation reservation = new Reservation(member, car, date);
        reservationRepository.save(reservation);
    }

    public List<ReservationResponse> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        List<ReservationResponse> response = reservations.stream().map(reservation -> new ReservationResponse(reservation)).collect(Collectors.toList());

        return response;
    }
}
