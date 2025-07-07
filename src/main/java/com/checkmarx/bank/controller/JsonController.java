package com.checkmarx.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/json")
public class JsonController {
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @PostMapping("/deserialize")
    public Object deserializeJson(@RequestBody String json) throws IOException {
        return objectMapper.readValue(json, Object.class);
    }
    
    @PostMapping("/parse")
    public Object parseJson(@RequestBody String json) throws IOException {
        objectMapper.enableDefaultTyping();
        return objectMapper.readValue(json, Object.class);
    }
} 