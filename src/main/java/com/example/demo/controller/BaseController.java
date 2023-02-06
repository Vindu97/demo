package com.example.demo.controller;
import java.io.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.URL;

import org.springframework.boot.context.annotation.Configurations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.configuration.ConfigReader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BaseController {

    private final ConfigReader configurations;

    @GetMapping("/")
    public String indexPage() {
        log.debug("Index Page Opened");
        return "index";
    }

    @GetMapping("/v/{droneId}")
    public String getVideoFeed(Model model, @PathVariable("droneId") String droneId) {
        model.addAttribute("publicId", getPublicIpAddress());
        model.addAttribute("droneId",droneId);
        model.addAttribute("videoEndpoint", configurations.getVideoWsEndpoint());

        return "video";
    }

    private String getPublicIpAddress(){
        String ip = "";

        try{
            final URL whatismyip = new URL("http://checkip.amazonaws.com");

            try(final BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()))){
                ip = in.readLine();
            }
            
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return ip;
    }
}
