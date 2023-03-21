package hello.mongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class MongoApplication implements CommandLineRunner {
	private final CustomerRepository customerRepository;

	public MongoApplication(final CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		customerRepository.deleteAll();

		customerRepository.save(new Customer("Kim", "SeolYoung"));
		customerRepository.save(new Customer("Park", "SeolYoung"));

		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : customerRepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		System.out.println("Customer found with findByFirstName('Kim'):");
		System.out.println("--------------------------------");
		System.out.println(customerRepository.findByFirstName("Kim"));
	}
}
