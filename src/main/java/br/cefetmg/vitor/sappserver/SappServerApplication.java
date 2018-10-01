package br.cefetmg.vitor.sappserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.cefetmg.vitor.sappserver.broker.InitBroker;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class SappServerApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SappServerApplication.class, args);
		
		context.getBean(InitDataBase.class).initDataBase();
		context.getBean(InitBroker.class).startBroker();
	}

}
