package fact.it.carservice.service;

import fact.it.carservice.dto.CarRequest;
import fact.it.carservice.dto.CarResponse;
import fact.it.carservice.model.Car;
import fact.it.carservice.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    // Create a new car
    public void createCar(CarRequest carRequest) {
        String carCode = generateCarCode(carRequest.getBrand(), carRequest.getModel());

        // Ensure carCode is unique
        if (carRepository.findByCarCode(carCode).isPresent()) {
            throw new RuntimeException("Car with carCode " + carCode + " already exists.");
        }

        Car car = Car.builder()
                .id(UUID.randomUUID().toString()) // Generate unique ID
                .carCode(carCode)
                .brand(carRequest.getBrand())
                .model(carRequest.getModel())
                .price(carRequest.getPrice())
                .year(carRequest.getYear())
                .stock(carRequest.getStock()) // Initialize stock
                .build();

        carRepository.save(car);
    }

    private String generateCarCode(String brand, String model) {
        // Generate a readable car code with a random number
        int randomSuffix = (int) (Math.random() * 9000) + 1000; // Random 4-digit number
        return String.format("CAR-%s-%s-%d", brand.toUpperCase(), model.toUpperCase(), randomSuffix);
    }

    // Adjust stock when a car is hired by carCode
    public void decreaseStockByCarCode(String carCode, int quantity) {
        Car car = carRepository.findByCarCode(carCode)
                .orElseThrow(() -> new RuntimeException("Car not found with carCode: " + carCode));

        if (car.getStock() < quantity) {
            throw new RuntimeException("Not enough stock available for carCode: " + carCode);
        }

        car.setStock(car.getStock() - quantity);
        carRepository.save(car);
    }

    // Adjust stock when a car is returned by carCode
    public void increaseStockByCarCode(String carCode, int quantity) {
        Car car = carRepository.findByCarCode(carCode)
                .orElseThrow(() -> new RuntimeException("Car not found with carCode: " + carCode));

        car.setStock(car.getStock() + quantity);
        carRepository.save(car);
    }

    // Get all cars
    public List<CarResponse> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(this::mapToCarResponse)
                .collect(Collectors.toList());
    }

    // Get a car by ID
    public CarResponse getCarById(String id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
        return mapToCarResponse(car);
    }

    // Get a car by carCode
    public CarResponse getCarByCarCode(String carCode) {
        Car car = carRepository.findByCarCode(carCode)
                .orElseThrow(() -> new RuntimeException("Car not found with carCode: " + carCode));
        return mapToCarResponse(car);
    }

    // Delete a car by ID
    public void deleteCarById(String id) {
        if (!carRepository.existsById(id)) {
            throw new RuntimeException("Car not found with id: " + id);
        }
        carRepository.deleteById(id);
    }

    // Map Car to CarResponse
    private CarResponse mapToCarResponse(Car car) {
        return CarResponse.builder()
                .id(car.getId()) // Include car ID
                .carCode(car.getCarCode()) // Fix getter method
                .brand(car.getBrand())
                .model(car.getModel())
                .price(car.getPrice())
                .year(car.getYear())
                .stock(car.getStock()) // Include stock in response
                .build();
    }
}
