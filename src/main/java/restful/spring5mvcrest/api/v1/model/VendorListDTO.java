package restful.spring5mvcrest.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VendorListDTO {
    @ApiModelProperty(value = "List of all vendors",required = true)
    List<VendorDTO> vendors;
}
