package az.spring.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("db/database.properties")
@Data
public class DatabaseConfig {


    @Value("${jdbc.DriverClassName}")
    private String DriverClassName;
    @Value("${jdbc.Url}")
    private String Url;
    @Value("${jdbc.Username}")
    private String Username;
    @Value("${jdbc.Password}")
    private String Password;

}
