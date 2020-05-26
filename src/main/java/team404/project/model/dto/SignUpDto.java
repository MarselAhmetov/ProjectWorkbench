package team404.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {
    @Email(message = "{error.incorrect.email}")
    private String email;
    @NotBlank(message = "{error.null.username}")
    private String username;
    @Size(min = 3, max = 12, message = "{error.incorrect.password}")
    private String password;
}
