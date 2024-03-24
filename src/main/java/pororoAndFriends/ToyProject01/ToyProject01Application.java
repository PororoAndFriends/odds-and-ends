package pororoAndFriends.ToyProject01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ToyProject01Application {

	public static void main(String[] args) {
		SpringApplication.run(ToyProject01Application.class, args);
	}

}
