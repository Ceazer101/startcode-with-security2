package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CarServiceMockitoTest {

    @Mock
    CarRepository carRepository;

    @Autowired
    CarService carService;

    @BeforeEach
    public void setup(){
        carService = new CarService(carRepository);
    }

    @Test
    void addCar() throws Exception {
        Car c = new Car("Volkswagen", "Polo", 1000, 100);
        c.setId(1);
        Mockito.when(carRepository.save(any(Car.class))).thenReturn(c);
        CarRequest request = new CarRequest(c);
        CarResponse found = carService.addCar(request);
        assertEquals("Volkswagen", found.getBrand());
    }

    @Test
    void editCar() {
    }

    @Test
    void getCarsTest() {
        Mockito.when(carRepository.findAll()).thenReturn(List.of(
                new Car("Volkswagen", "Polo", 1000, 100),
                new Car("Toyota", "Corolla", 2000, 200)
        ));
        List<CarResponse> cars = carService.getCars();
        assertEquals(2, cars.size());
    }

    @Test
    void findCarByIdTest() throws Exception {
        Car c = new Car("Volkswagen", "Polo", 1000, 100);
        c.setId(1);
        Mockito.when(carRepository.findById(c.getId())).thenReturn(Optional.of(c));

        CarResponse response = carService.findCarById(1);
        assertEquals(1 ,response.getId());
    }

    @Test
    void setPricePrDayForCar() {
    }

    @Test
    void setBestDiscountForCar() {
    }

    @Test
    void deleteById() {
    }
}