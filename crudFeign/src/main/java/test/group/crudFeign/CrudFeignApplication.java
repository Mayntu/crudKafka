package test.group.crudFeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CrudFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudFeignApplication.class, args);
	}

}
