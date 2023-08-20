package ru.sobse.spring_boot.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sobse.spring_boot.systemprofile.SystemProfile;

@RestController
public class Controller {
    private SystemProfile systemProfile;

    public Controller(SystemProfile systemProfile) {
        this.systemProfile = systemProfile;
    }

    @GetMapping("/")
    public String main() {
        return systemProfile.getProfile();
    }
}
