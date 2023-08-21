package ru.sobse.spring_boot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sobse.spring_boot.systemprofile.DevProfile;
import ru.sobse.spring_boot.systemprofile.ProductionProfile;
import ru.sobse.spring_boot.systemprofile.SystemProfile;

@Configuration
public class Config {

    @Bean
    @ConditionalOnProperty(prefix = "profile", name = "dev", havingValue = "true", matchIfMissing = true)
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(prefix = "profile", name = "dev", havingValue = "false")
    public SystemProfile productionProfile() {
        return new ProductionProfile();
    }
}
