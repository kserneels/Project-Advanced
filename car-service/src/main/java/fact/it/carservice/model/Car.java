package fact.it.carservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cars")
public class Car {
    @Id
    private String id;
    private String carCode;
    private String brand;
    private String model;
    private BigDecimal price;
    private int year;
    private int stock; // New field for stock management
}
