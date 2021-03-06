package team404.project.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team404.project.model.enums.Currency;
import team404.project.model.enums.DebtStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long debtCount;
    private String description;
    private String debtorName;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private DebtStatus status;

    @Transient
    private Double priority;

    @ManyToOne
    private User debtor;

    @ManyToOne
    private User owner;
}
