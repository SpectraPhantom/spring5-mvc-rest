package restful.spring5mvcrest.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import restful.spring5mvcrest.api.v1.model.VendorDTO;
import restful.spring5mvcrest.api.v1.model.VendorListDTO;
import restful.spring5mvcrest.services.VendorService;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors() {
        return new VendorListDTO(vendorService.getAllVendors());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO addVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.addNewVendor(vendorDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO putVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.updateVendor(id, vendorDTO);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patchVendor(id, vendorDTO);
    }
}
