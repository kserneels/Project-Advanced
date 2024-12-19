package fact.it.carhireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarHireResponse {
    private String contractCode;    // Contract code
    private String customerCode;    // Use customerCode
    private String customerName;    // Name of the customer (use customerName from CustomerResponse)
    private String carCode;         // Use carCode
    private String carModel;
    private BigDecimal price;
    private LocalDate hireDate;
    private LocalDate returnDate;
}
