package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@Component  
public class ConfigReader {
    
    @Value("${app.drone.video-ws-endpoint}")
    private String videoWsEndpoint;

    @Value("${app.drone.video-server-port}")
    private int videoServerPort;

    @Value("${app.drone.drone-id-length}")
    private int droneIdLength;

}

