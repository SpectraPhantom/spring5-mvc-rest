package restful.spring5mvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import restful.spring5mvcrest.domain.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
