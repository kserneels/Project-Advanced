package fact.it.carservice.repository;

import fact.it.carservice.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CarRepository extends MongoRepository<Car, String> {
    Optional<Car> findByCarCode(String carCode); // Ensure this method returns Optional<Car>
}
