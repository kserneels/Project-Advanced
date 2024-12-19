package fact.it.carhireservice.repository;

import fact.it.carhireservice.model.CarHire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarHireRepository extends JpaRepository<CarHire, Long> {
    // Find all contracts
    List<CarHire> findAll();

    // Find contracts by customer code
    List<CarHire> findByCustomerCode(String customerCode);
}
