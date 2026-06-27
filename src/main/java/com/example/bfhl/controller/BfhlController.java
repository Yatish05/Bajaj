package com.example.bfhl.controller;

import com.example.bfhl.dto.BfhlRequestDto;
import com.example.bfhl.dto.BfhlResponseDto;
import com.example.bfhl.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping("/bfhl")
    public ResponseEntity<BfhlResponseDto> processInput(@RequestBody BfhlRequestDto requestDto) {
        BfhlResponseDto responseDto = bfhlService.processData(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/bfhl")
    public ResponseEntity<Map<String, Object>> getOperationCode() {
        Map<String, Object> response = new HashMap<>();
        response.put("operation_code", 1);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> getHealth() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        return ResponseEntity.ok(response);
    }
}
