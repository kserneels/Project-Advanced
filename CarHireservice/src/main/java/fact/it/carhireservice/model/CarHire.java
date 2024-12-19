package fact.it.carhireservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "car_hires")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarHire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Change to AUTO or use UUID generation
    private UUID id;  // Use UUID as the primary key

    private String contractCode; // Contract code (human-readable)

    private String customerCode; // Use customerCode instead of customerId
    private String carCode; // Use carCode instead of carId

    private LocalDate hireDate;
    private LocalDate returnDate;

    // Generate contract code when the contract is created
    @PrePersist
    public void generateContractDetails() {
        this.contractCode = "CONTRACT-" + UUID.randomUUID().toString().substring(0, 8); // Example: CONTRACT-1234ABCD
    }
}
