package com.edwardjones.reminder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class ReminderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReminderApplication.class, args);
	}
	
	/**
	 * App Startup Bean
	 * @return https://portal.azure.com/#@rc-jones.com/resource/subscriptions/e1ff64ae-d437-4ed9-bafa-4cb2846fb181/resourceGroups/wildcards/providers/Microsoft.Web/sites/codefest-stickynote-app/logStream
	 */
	@Bean(initMethod="init")
	public AppStartupBean appStartupBean() {
	    return new AppStartupBean();
	}
	

}
