package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by PavelGrudina on 16.06.2017.
 */
@Configuration
@Import(PersistenceConfig.class)
@ComponentScan("services")
public class RootConfig {
}
