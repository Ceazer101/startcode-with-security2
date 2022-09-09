package dat3.cars.repository;

import dat3.cars.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

    boolean existsByCar1_IdAndRentalDate(int carId, LocalDate date);
}
