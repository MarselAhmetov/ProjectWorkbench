package team404.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DebtDto {
    private String whos;
    private String debtorName;
    private Integer friendId;
    private Long debtCount;
    private String description;
    private String date;
    private String currency;
}
