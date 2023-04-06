package za.co.makgwalet.bankingapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

   // Enum for transaction type (credit or debit)
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private LocalDateTime timestamp; // Timestamp of the transaction


    @ManyToOne
    @JoinColumn(name = "source_account_id")
    private Account sourceAccount; // Many-to-one relationship with source Account entity

    @ManyToOne
    @JoinColumn(name = "destination_account_id")
    private Account destinationAccount; // Many-to-one relationship with destination Account entity

}
