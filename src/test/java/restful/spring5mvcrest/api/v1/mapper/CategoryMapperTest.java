package restful.spring5mvcrest.api.v1.mapper;

import org.junit.jupiter.api.Test;
import restful.spring5mvcrest.api.v1.model.CategoryDTO;
import restful.spring5mvcrest.domain.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    CategoryMapper categoryMapper=CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDTO() {
        Category category=new Category();
        category.setName("Joe");
        category.setId(1L);

        CategoryDTO categoryDTO= categoryMapper.categoryToCategoryDTO(category);

        assertEquals("Joe",categoryDTO.getName());
        assertEquals(1L,categoryDTO.getId());
    }
}