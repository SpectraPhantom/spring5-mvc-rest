package restful.spring5mvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import restful.spring5mvcrest.domain.Vendor;

public interface VendorRepository extends JpaRepository<Vendor,Long> {
}
