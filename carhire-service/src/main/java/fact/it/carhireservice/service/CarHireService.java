package fact.it.carhireservice.service;

import fact.it.carhireservice.dto.CarHireRequest;
import fact.it.carhireservice.dto.CarHireResponse;
import fact.it.carhireservice.dto.CarResponse;
import fact.it.carhireservice.dto.CustomerResponse;
import fact.it.carhireservice.model.CarHire;
import fact.it.carhireservice.repository.CarHireRepository;  // Import repository
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarHireService {

    private final CarHireRepository carHireRepository;
    private final WebClient.Builder webClientBuilder;

    public CarHireService(WebClient.Builder webClientBuilder, CarHireRepository carHireRepository) {
        this.webClientBuilder = webClientBuilder;
        this.carHireRepository = carHireRepository;
    }

    public CarHireResponse createCarHireContract(CarHireRequest request) {
        // Step 1: Fetch Customer Data by customerCode
        CustomerResponse customer = fetchCustomerData(request.getCustomerCode());
        if (customer == null) {
            throw new RuntimeException("Customer not found.");
        }

        // Step 2: Fetch Car Data by carCode
        CarResponse car = fetchCarData(request.getCarCode());
        if (car == null || car.getStock() <= 0) {
            throw new RuntimeException("Car is not available in stock.");
        }

        // Step 3: Decrease stock in CarService
        decreaseCarStock(car.getCarCode());

        // Step 4: Create and save the CarHire contract
        CarHire carHire = new CarHire();
        carHire.setCustomerCode(customer.getCustomerCode());
        carHire.setCarCode(car.getCarCode());
        carHire.setHireDate(request.getHireDate());
        carHire.setReturnDate(request.getReturnDate());
        carHireRepository.save(carHire);

        return buildCarHireResponse(carHire, car, customer);
    }

    public List<CarHireResponse> getAllCarHireContracts() {
        List<CarHire> carHires = carHireRepository.findAll(); // Fetch all car hire records from the repository
        return carHires.stream()
                .map(carHire -> {
                    CustomerResponse customer = fetchCustomerData(carHire.getCustomerCode());
                    CarResponse car = fetchCarData(carHire.getCarCode());
                    return buildCarHireResponse(carHire, car, customer);
                })
                .collect(Collectors.toList());
    }

    public List<CarHireResponse> getCarHireContractsByCustomerCode(String customerCode) {
        List<CarHire> carHires = carHireRepository.findByCustomerCode(customerCode);

        return carHires.stream()
                .map(carHire -> {
                    CustomerResponse customer = fetchCustomerData(carHire.getCustomerCode());
                    CarResponse car = fetchCarData(carHire.getCarCode());
                    return buildCarHireResponse(carHire, car, customer);
                })
                .collect(Collectors.toList());
    }

    private CarResponse fetchCarData(String carCode) {
        return webClientBuilder.baseUrl("http://localhost:8080")
                .build()
                .get()
                .uri("/api/cars/by-code/{carCode}", carCode)
                .retrieve()
                .bodyToMono(CarResponse.class)
                .block();
    }

    private CustomerResponse fetchCustomerData(String customerCode) {
        return webClientBuilder.baseUrl("http://localhost:8082")
                .build()
                .get()
                .uri("/api/customers/by-code/{customerCode}", customerCode)
                .retrieve()
                .bodyToMono(CustomerResponse.class)
                .block();
    }

    private void decreaseCarStock(String carCode) {
        String uri = String.format("http://localhost:8080/api/cars/decrease-stock/%s?quantity=%d", carCode, 1);

        webClientBuilder.baseUrl("http://localhost:8080")
                .build()
                .put()
                .uri(uri)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    private CarHireResponse buildCarHireResponse(CarHire carHire, CarResponse car, CustomerResponse customer) {
        return CarHireResponse.builder()
                .contractCode(carHire.getContractCode())
                .customerCode(carHire.getCustomerCode())
                .customerName(customer != null ? customer.getUsername() : "Unknown")
                .carCode(carHire.getCarCode())
                .carModel(car != null ? car.getModel() : "Unknown")
                .price(car != null ? car.getPrice() : BigDecimal.ZERO)
                .hireDate(carHire.getHireDate())
                .returnDate(carHire.getReturnDate())
                .build();
    }
}
