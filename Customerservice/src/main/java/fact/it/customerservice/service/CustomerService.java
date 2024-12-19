package fact.it.customerservice.service;

import fact.it.customerservice.dto.CustomerRequest;
import fact.it.customerservice.dto.CustomerResponse;
import fact.it.customerservice.model.Customer;
import fact.it.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // Create a new customer
    public void createCustomer(CustomerRequest customerRequest) {
        String generatedId = UUID.randomUUID().toString();
        String customerCode = generateCustomerCode(customerRequest.getUsername());

        Customer customer = new Customer();
        customer.setId(generatedId); // Assign the generated UUID
        customer.setCustomerCode(customerCode); // Assign the generated customer code
        customer.setUsername(customerRequest.getUsername());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhone(customerRequest.getPhone());
        customer.setAddress(customerRequest.getAddress());

        customerRepository.save(customer);
    }

    private String generateCustomerCode(String username) {
        // Generate a unique customer code based on username and a random number
        int randomSuffix = (int) (Math.random() * 9000) + 1000; // Random 4-digit number
        return String.format("CUST-%s-%d", username.toUpperCase(), randomSuffix);
    }

    // Get all customers
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToCustomerResponse)
                .toList();
    }

    public CustomerResponse getCustomerByCustomerCode(String customerCode) {
        Customer customer = customerRepository.findByCustomerCode(customerCode)
                .orElseThrow(() -> new RuntimeException("Customer not found with customerCode: " + customerCode));
        return mapToCustomerResponse(customer);
    }

    // Get a customer by ID
    public CustomerResponse getCustomerById(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        return mapToCustomerResponse(customer);
    }

    // Update customer details
    @Transactional
    public void updateCustomer(String id, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        customer.setUsername(customerRequest.getUsername());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhone(customerRequest.getPhone());
        customer.setAddress(customerRequest.getAddress());

        customerRepository.save(customer);
    }

    // Delete customer by ID
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    // Map Customer to CustomerResponse
    private CustomerResponse mapToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .customerCode(customer.getCustomerCode())
                .username(customer.getUsername())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .build();
    }
}
