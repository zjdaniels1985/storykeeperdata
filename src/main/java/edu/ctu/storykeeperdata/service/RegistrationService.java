package edu.ctu.storykeeperdata.service;

import edu.ctu.storykeeperdata.model.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    public String register(RegistrationRequest request) {
        return "it works";
    }
}
