package empapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@SpringBootApplication
@Configuration
public class EmployeesApplication
{

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<ShallowEtagHeaderFilter> etagFilter(){
		FilterRegistrationBean<ShallowEtagHeaderFilter> registrationBean
				= new FilterRegistrationBean<>();
		registrationBean.setFilter(new ShallowEtagHeaderFilter());
		registrationBean.addUrlPatterns("/js/*");
		return registrationBean;
	}

}
