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
        Object obj = xstream.fromXML(xml);
        return obj.toString();
    }
    
    @PostMapping("/parse")
    public String parseXml(@RequestBody String xml) {
        xstream.allowTypes(new Class[]{Object.class});
        Object obj = xstream.fromXML(new ByteArrayInputStream(xml.getBytes()));
        return obj.toString();
    }
} 