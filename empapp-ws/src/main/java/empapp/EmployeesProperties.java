package empapp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@ConfigurationProperties(prefix = "employees")
@Validated
public class EmployeesProperties {

    @NotBlank
    private String urlPrefix;
}
