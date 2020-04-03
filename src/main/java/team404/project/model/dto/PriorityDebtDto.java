package team404.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team404.project.model.Debt;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriorityDebtDto {
    Debt debt;
    Long priority;
}
