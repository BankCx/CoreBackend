package com.checkmarx.bank.controller;

import com.thoughtworks.xstream.XStream;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/xml")
public class XmlController {
    
    private final XStream xstream = new XStream();
    
    @PostMapping("/deserialize")
    public String deserializeXml(@RequestBody String xml) {
        // Intentionally vulnerable - using vulnerable XStream version with no security restrictions
        // CVE-2020-26217: Allows arbitrary code execution through XML deserialization
        Object obj = xstream.fromXML(xml);
        return obj.toString();
    }
    
    @PostMapping("/parse")
    public String parseXml(@RequestBody String xml) {
        // Intentionally vulnerable - using vulnerable XStream version with no type restrictions
        // CVE-2020-26217: Allows arbitrary class loading through XML deserialization
        xstream.allowTypes(new Class[]{Object.class}); // Allows any class to be deserialized
        Object obj = xstream.fromXML(new ByteArrayInputStream(xml.getBytes()));
        return obj.toString();
    }
} 