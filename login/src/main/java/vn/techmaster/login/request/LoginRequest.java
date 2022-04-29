package vn.techmaster.login.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Email cannot blank")
    @Email(message = "Invalid email")
    private  String email;

    @Size(min = 5,max = 20,message = "Password length must between 5 and 20  character")
    private  String password;
}
