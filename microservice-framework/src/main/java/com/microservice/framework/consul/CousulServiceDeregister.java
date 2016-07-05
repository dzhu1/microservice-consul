package com.microservice.framework.consul;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;

public class CousulServiceDeregister implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
        Consul consul = event.getApplicationContext().getBean(Consul.class);
        ConsulProperties prop = event.getApplicationContext().getBean(ConsulProperties.class);

        AgentClient agentClient = consul.agentClient();
        agentClient.deregister(prop.getServicename());
	}

}
