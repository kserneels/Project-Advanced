package fact.it.carhireservice.controller;

import fact.it.carhireservice.dto.CarHireRequest;
import fact.it.carhireservice.dto.CarHireResponse;
import fact.it.carhireservice.service.CarHireService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carhire")
public class CarHireController {

    private final CarHireService carHireService;

    // Constructor injection for CarHireService
    public CarHireController(CarHireService carHireService) {
        this.carHireService = carHireService;
    }

    // Create a new car hire contract
    @PostMapping("/create")
    public CarHireResponse createCarHireContract(@RequestBody CarHireRequest request) {
        // Delegate the car hire contract creation to the service
        return carHireService.createCarHireContract(request);
    }

    @GetMapping("/all")
    public List<CarHireResponse> getAllCarHireContracts() {
        return carHireService.getAllCarHireContracts();
    }

    @GetMapping("/{customerCode}")
    public List<CarHireResponse> getCarHireContractsByCustomerCode(@PathVariable String customerCode) {
        return carHireService.getCarHireContractsByCustomerCode(customerCode);
    }
//    // Other endpoints related to car hire could be added here (e.g., get, update, delete)
}
