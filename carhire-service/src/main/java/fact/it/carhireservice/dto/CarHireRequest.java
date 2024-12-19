package fact.it.carhireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarHireRequest {
    private String customerCode;  // Change to customerCode instead of customerId
    private String carCode;       // Change to carCode instead of carId
    private LocalDate hireDate;
    private LocalDate returnDate;
}
