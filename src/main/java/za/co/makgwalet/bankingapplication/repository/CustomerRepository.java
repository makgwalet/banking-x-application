package za.co.makgwalet.bankingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.makgwalet.bankingapplication.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
