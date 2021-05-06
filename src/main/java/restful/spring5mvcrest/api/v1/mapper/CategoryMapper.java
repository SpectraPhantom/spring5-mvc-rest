package restful.spring5mvcrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import restful.spring5mvcrest.api.v1.model.CategoryDTO;
import restful.spring5mvcrest.domain.Category;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE= Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
