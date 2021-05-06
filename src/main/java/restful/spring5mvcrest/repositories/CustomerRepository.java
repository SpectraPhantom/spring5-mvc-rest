package restful.spring5mvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import restful.spring5mvcrest.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer getCustomerById(Long id);
}
