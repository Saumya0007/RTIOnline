package in.gov.rti.loginresp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRespDTO {
    private String username;
    private String role;
    private Integer ucode;
    private String login_active;
}

