package restful.spring5mvcrest.services;

import org.springframework.stereotype.Service;
import restful.spring5mvcrest.api.v1.mapper.VendorMapper;
import restful.spring5mvcrest.api.v1.model.VendorDTO;
import restful.spring5mvcrest.controllers.v1.VendorController;
import restful.spring5mvcrest.domain.Vendor;
import restful.spring5mvcrest.repositories.VendorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO returnDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        returnDTO.setVendorUrl(getVendorUrl(savedVendor.getId()));

        return returnDTO;
    }

    @Override
    public VendorDTO addNewVendor(VendorDTO vendorDTO) {
        return saveAndReturnDTO(vendorMapper.vendorDtoToVendor(vendorDTO));
    }

    @Override
    public void deleteById(Long id) {
        vendorRepository.deleteById(id);
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);
        vendor.setId(id);

        return saveAndReturnDTO(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(
                vendor -> {
                    if (vendorDTO.getName() != null) {
                        vendor.setName(vendorDTO.getName());
                    }
                    VendorDTO returnedDTO = vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
                    returnedDTO.setVendorUrl(getVendorUrl(id));
                    return returnedDTO;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    public String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }
}
