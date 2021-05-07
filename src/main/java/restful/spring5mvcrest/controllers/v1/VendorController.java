package restful.spring5mvcrest.controllers.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import restful.spring5mvcrest.api.v1.model.VendorDTO;
import restful.spring5mvcrest.api.v1.model.VendorListDTO;
import restful.spring5mvcrest.services.VendorService;

@Api
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "Return list of Vendors")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors() {
        return new VendorListDTO(vendorService.getAllVendors());
    }

    @ApiOperation(value = "Return single Vendor by id")
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @ApiOperation(value = "Save Vendor")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO addVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.addNewVendor(vendorDTO);
    }

    @ApiOperation(value = "Delete Vendor by ID")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteById(id);
    }

    @ApiOperation(value = "Put single Vendor by ID and request body")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO putVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.updateVendor(id, vendorDTO);
    }

    @ApiOperation(value = "Patch single Vendor by ID and request body")
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patchVendor(id, vendorDTO);
    }
}
