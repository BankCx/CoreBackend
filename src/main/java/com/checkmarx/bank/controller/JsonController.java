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
        // Intentionally vulnerable - using vulnerable jackson-databind version
        // CVE-2022-42003: Allows arbitrary code execution through JSON deserialization
        return objectMapper.readValue(json, Object.class);
    }
    
    @PostMapping("/parse")
    public Object parseJson(@RequestBody String json) throws IOException {
        // Intentionally vulnerable - using vulnerable jackson-databind version with no type restrictions
        // CVE-2022-42003: Allows arbitrary class loading through JSON deserialization
        objectMapper.enableDefaultTyping(); // Enables polymorphic type handling
        return objectMapper.readValue(json, Object.class);
    }
} 