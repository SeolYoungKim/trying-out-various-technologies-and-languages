package hello.mongo;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody CustomerRequest customerRequest) {
        return customerRepository.save(new Customer(customerRequest.firstName(), customerRequest.lastName()));
    }

    @GetMapping
    public Customer findByFirstName(@RequestParam String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    @GetMapping("/all")
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
