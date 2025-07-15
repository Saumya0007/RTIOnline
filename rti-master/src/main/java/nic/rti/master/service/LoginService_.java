package in.gov.rti.implementation;


import in.gov.rti.entity.User_;
import in.gov.rti.loginresp.LoginRespDTO;
import in.gov.rti.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService_ {

    @Autowired
    private UserRepository userRepository;

    public LoginRespDTO authenticate(String username, String password) {
        Optional<User_> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            return new LoginRespDTO(username, null, null, "InValidUser");
        }

        User_ user = optionalUser.get();

        // Match password
        if (!user.getPassword().equals(password)) {
            return new LoginRespDTO(username, null, null, "InValidUser");
        }

        // Check status via flage, active_idle, password_change, etc.
        String status;

        if (user.getFlage() != null && user.getFlage() == 1) {
            status = "BlockUser";
        } else if ("N".equalsIgnoreCase(user.getActiveIdle())) {
            status = "BlockPass";
        } else if ("Y".equalsIgnoreCase(user.getPasswordChange())) {
            status = "ChangePass";
        } else if ("Y".equalsIgnoreCase(user.getActiveIdle())) {
            status = "Active";
        } else {
            status = "AlreadyActive"; // Or handle logic better based on exact rules
        }

        return new LoginRespDTO(user.getUsername(), user.getRole(), user.getUncode(), status);
    }
}
