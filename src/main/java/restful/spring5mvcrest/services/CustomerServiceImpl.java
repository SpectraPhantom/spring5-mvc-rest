package restful.spring5mvcrest.services;

import org.springframework.stereotype.Service;
import restful.spring5mvcrest.api.v1.mapper.CustomerMapper;
import restful.spring5mvcrest.api.v1.model.CustomerDTO;
import restful.spring5mvcrest.controllers.v1.CustomerController;
import restful.spring5mvcrest.domain.Customer;
import restful.spring5mvcrest.repositories.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
                    return customerDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveAndReturnDTO(customerMapper.customerDTOToCustomer(customerDTO));
    }

    @Override
    public CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnedCustomer = customerMapper.customerToCustomerDTO(savedCustomer);
        returnedCustomer.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));

        return returnedCustomer;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);
        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id)
                .map(customer -> {
                    if (customerDTO.getFirstName() != null) {
                        customer.setFirstName(customerDTO.getFirstName());
                    }
                    if (customerDTO.getLastName() != null) {
                        customer.setLastName(customerDTO.getLastName());
                    }
                    CustomerDTO returnedDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
                    returnedDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
                    return returnedDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    private String getCustomerUrl(Long id) {
        return CustomerController.BASE_URL + "/" + id;
    }
}
