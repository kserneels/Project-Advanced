package fact.it.customerservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private String id; // UUID as the primary key
    private String customerCode; // Unique customer code
    private String username;
    private String email;
    private String phone;
    private String address;
}
