package com.microservice.framework.consul;

import java.net.MalformedURLException;
import java.net.URI;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;

/**
 * 监听contextrefresh事件
 */
public class ConsulServiceRegister implements ApplicationListener<ContextRefreshedEvent> {
	
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Consul consul = event.getApplicationContext().getBean(Consul.class);
        ConsulProperties prop = event.getApplicationContext().getBean(ConsulProperties.class);

        AgentClient agentClient = consul.agentClient();
        try {
            agentClient.register(prop.getServicePort(), 
                                 URI.create(prop.getHealthUrl()).toURL(),
                                 prop.getHealthInterval(), 
                                 prop.getServicename(), 
                                 prop.getServicename(), // serviceId:
                                 prop.getServiceTag());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}