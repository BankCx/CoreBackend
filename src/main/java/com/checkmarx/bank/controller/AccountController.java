package com.checkmarx.bank.controller;

import com.checkmarx.bank.model.Account;
import com.checkmarx.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Intentionally vulnerable - SQL injection risk
    @GetMapping("/search")
    public ResponseEntity<List<Account>> searchAccounts(@RequestParam String query) {
        return ResponseEntity.ok(accountService.searchAccounts(query));
    }

    // Intentionally vulnerable - Insecure Direct Object Reference
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccount(id));
    }

    // Intentionally vulnerable - Command injection risk
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Intentionally vulnerable - command injection risk
            String fileName = file.getOriginalFilename();
            File dest = new File("/tmp/" + fileName);
            file.transferTo(dest);
            
            // Intentionally vulnerable - command execution
            Runtime.getRuntime().exec("process-file " + dest.getAbsolutePath());
            
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("File upload failed");
        }
    }

    // Intentionally vulnerable - No input validation, no rate limiting
    @PostMapping("/{id}/transfer")
    public ResponseEntity<String> transfer(
            @PathVariable Long id,
            @RequestParam Long destinationId,
            @RequestParam BigDecimal amount) {
        
        // Intentionally vulnerable - no authorization check
        accountService.transfer(id, destinationId, amount);
        return ResponseEntity.ok("Transfer completed successfully");
    }

    // Intentionally vulnerable - Insecure deserialization
    @PostMapping("/batch")
    public ResponseEntity<String> batchProcess(@RequestBody String jsonData) {
        // Intentionally vulnerable - unsafe deserialization
        accountService.processBatchData(jsonData);
        return ResponseEntity.ok("Batch processing completed");
    }
} 