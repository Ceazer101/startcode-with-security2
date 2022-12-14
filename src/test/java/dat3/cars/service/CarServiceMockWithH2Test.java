package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarServiceMockWithH2Test {

    public CarService carService;

    public static CarRepository carRepository;

    private static int carId1, carId2;

    @BeforeAll
    public static void setupData(@Autowired CarRepository car_Repository){
        carRepository = car_Repository;
        carRepository.deleteAll();
        Car car1 = new Car("Volkswagen", "Polo", 1000, 100);
        Car car2 = new Car("Toyota", "Corolla", 2000, 200);
        carRepository.save(car1);
        carRepository.save(car2);
        carId1 = car1.getId();
        carId2 = car2.getId();
    }

    @BeforeEach
    public void setCarService(){
        carService = new CarService(carRepository);
    }

    @Test
    void addCar() {
        Car c = new Car("Volkswagen", "Polo", 1000, 100);
        CarRequest request = new CarRequest(c);
        carService.addCar(request);
        assertEquals(3,carRepository.count());
    }

    //unit test works only works with id = 3
    //but maven test will fail
    @Test
    void editCar() throws Exception {
        Car carToEdit = carRepository.findById(carId1).get();
        carToEdit.setPricePrDay(55);
        carToEdit.setBestDiscount(80);
        CarRequest request = new CarRequest(carToEdit);

        carService.editCar(request, carId1);
        Car testCar = carRepository.findById(carId1).get();
        assertEquals(55, testCar.getPricePrDay());
        assertEquals(80, testCar.getBestDiscount());
    }

    @Test
    void getCars() {
        List<CarResponse> response = carService.getCars();
        assertEquals(2,response.size());
        assertThat(response, containsInAnyOrder(hasProperty("brand", is("Volkswagen")), hasProperty("brand", is("Toyota"))));
    }

    @Test
    void findCarById() throws Exception {
        Car c = new Car("Volkswagen", "Polo", 1000, 100);
        carRepository.save(c);
        CarResponse response = carService.findCarById(c.getId());
        assertEquals("Volkswagen",response.getBrand());
    }

    @Test
    void setPricePrDayForCar() {
    }

    @Test
    void setBestDiscountForCar() {
    }

    @Test
    void deleteById() {
        Car c = new Car("Volkswagen", "Polo", 1000, 100);
        carRepository.save(c);
        carRepository.deleteById(c.getId());
        assertEquals(2,carRepository.count());
        System.out.println(c);
    }
}