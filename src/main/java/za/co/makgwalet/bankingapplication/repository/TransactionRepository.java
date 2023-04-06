package za.co.makgwalet.bankingapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.makgwalet.bankingapplication.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository <Transaction, Long> {
}
