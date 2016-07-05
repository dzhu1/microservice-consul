package com.microservice.framework;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.microservice.framework.consul.ConsulServiceRegister;

@SpringBootApplication
public class MicroServiceAppLauncher extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {
	private ApplicationListener<ContextRefreshedEvent> listener = new ConsulServiceRegister();
	
	@Value("${service.port:8080}")
	private int port;
	
	private Class<?> externalLoadClass;
	
	public MicroServiceAppLauncher() {
		
	}
	
	public MicroServiceAppLauncher(Class<?> externalLoadClass){
		this.externalLoadClass = externalLoadClass;
	}

	
	public void run(String[] args) {
		SpringApplication sa = new SpringApplication(new Object[] {this.externalLoadClass ,MicroServiceAppLauncher.class});
		sa.addListeners(this.listener);
		sa.run(args);
	}

	public static void main(String[] args) {
		
	}
	
	public void addListener(ApplicationListener<ContextRefreshedEvent> listener) {
		this.listener = listener;
	}
	
    @Override  
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {  
        return builder.sources(MicroServiceAppLauncher.class);  
    }  

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(this.port);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}

