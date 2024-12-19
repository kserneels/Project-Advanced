package fact.it.carhireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {
    private String id;
    private String carCode;
    private String brand;
    private String model;
    private BigDecimal price;
    private int year;
    private int stock; // New field
}
