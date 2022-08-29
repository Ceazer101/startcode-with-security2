package dat3.cars.repository;

import dat3.cars.entity.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    static int c1;
    @BeforeAll
    public static void setUpData(@Autowired CarRepository carRepository){
        Car car1 = new Car("Toyota", "Corolla", 666, 50);

        carRepository.save(car1);

        c1 = car1.getId();
    }

    @Test
    public void testFindById(){
        Car found = carRepository.findById(c1).get();
        assertEquals(c1, found.getId());
        assertEquals("Toyota", found.getBrand());
    }

    @Test
    public void testFindByBrand(){
        Car found = carRepository.findCarByBrand("Toyota");
        assertEquals(c1, found.getId());
    }

}