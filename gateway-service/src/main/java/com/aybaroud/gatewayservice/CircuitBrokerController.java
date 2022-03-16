package com.aybaroud.gatewayservice;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CircuitBrokerController {

    @GetMapping("/defaultCountries")
    public Map<String,String> countries(){
        Map<String,String> data = new HashMap<>();
        data.put("message","defaut Countries");
        data.put("countries","France,Germany,Belguim...");
        return data;
    }


}
