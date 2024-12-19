package fact.it.carservice.controller;

import fact.it.carservice.dto.CarRequest;
import fact.it.carservice.dto.CarResponse;
import fact.it.carservice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCar(@RequestBody CarRequest carRequest) {
        carService.createCar(carRequest);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CarResponse> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarResponse getCarById(@PathVariable String id) {
        return carService.getCarById(id);
    }

    // Updated to use carCode for decreasing stock
    @PutMapping("/decrease-stock/{carCode}")
    @ResponseStatus(HttpStatus.OK)
    public void decreaseStock(@PathVariable String carCode, @RequestParam int quantity) {
        carService.decreaseStockByCarCode(carCode, quantity); // Use service method with carCode
    }

    // Updated to use carCode for increasing stock
    @PutMapping("/increase-stock/{carCode}")
    @ResponseStatus(HttpStatus.OK)
    public void increaseStock(@PathVariable String carCode, @RequestParam int quantity) {
        carService.increaseStockByCarCode(carCode, quantity); // Use service method with carCode
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCarById(@PathVariable String id) {
        carService.deleteCarById(id);
    }

    @GetMapping("/by-code/{carCode}")
    @ResponseStatus(HttpStatus.OK)
    public CarResponse getCarByCarCode(@PathVariable String carCode) {
        return carService.getCarByCarCode(carCode);  // Call service method to get car by carCode
    }

}
