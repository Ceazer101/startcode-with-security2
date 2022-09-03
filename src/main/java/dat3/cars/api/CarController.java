package dat3.cars.api;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public CarResponse addCar(@RequestBody CarRequest body) {
        return carService.addCar(body);
    }

    @GetMapping
    public List<CarResponse> getCars(){
        return carService.getCars();
    }

    @GetMapping(path = "/{id}")
    public CarResponse getCarById(@PathVariable int id) throws Exception { //Obviously we need to be able to limit this in a system with thousands of members
        CarResponse response = carService.findCarById(id);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable int id){
        carService.editCar(body,id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PatchMapping("/price/{id}/{value}")
    public void setPricePrDayForCar(@PathVariable int id, @PathVariable int value){
        carService.setPricePrDayForCar(id,value);
    }

    @PatchMapping("/discount/{id}/{value}")
    public void setBestDiscountForCar(@PathVariable int id, @PathVariable int value){
        carService.setBestDiscountForCar(id,value);
    }

    @DeleteMapping("/{id}")
    public void deleteCarById(@PathVariable int id){
        carService.deleteById(id);
    }
}
