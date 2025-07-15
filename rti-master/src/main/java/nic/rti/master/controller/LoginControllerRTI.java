package in.gov.rti.controller;

import in.gov.rti.dto.LoginReqDTO;
import in.gov.rti.implementation.LoginService_;
import in.gov.rti.loginresp.LoginRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/rti-nodal")
public class LoginControllerRTI {

    @Autowired
    private LoginService_ loginService;

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<LoginRespDTO> login(@RequestBody LoginReqDTO request) {
        LoginRespDTO response = loginService.authenticate(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(response);
    }
}

