package restful.spring5mvcrest.services;

import restful.spring5mvcrest.api.v1.model.VendorDTO;
import restful.spring5mvcrest.domain.Vendor;

import java.util.List;

public interface VendorService {
    List<VendorDTO> getAllVendors();

    VendorDTO getVendorById(Long id);

    VendorDTO saveAndReturnDTO(Vendor vendor);

    VendorDTO addNewVendor(VendorDTO vendorDTO);

    void deleteById(Long id);

    VendorDTO updateVendor(Long id,VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id,VendorDTO vendorDTO);
}
