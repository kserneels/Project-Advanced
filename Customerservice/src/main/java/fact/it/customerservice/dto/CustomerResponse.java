package fact.it.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private String id;            // UUID for the customer
    private String customerCode;  // Customer code
    private String username;
    private String email;
    private String phone;
    private String address;
}
