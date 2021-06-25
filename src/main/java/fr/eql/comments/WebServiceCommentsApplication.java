package fr.eql.comments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebServiceCommentsApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WebServiceCommentsApplication.class);
		app.setAdditionalProfiles("initData");
		app.run(args);
	}

}
