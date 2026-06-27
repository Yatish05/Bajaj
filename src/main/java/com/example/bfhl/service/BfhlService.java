package com.example.bfhl.service;

import com.example.bfhl.dto.BfhlRequestDto;
import com.example.bfhl.dto.BfhlResponseDto;

public interface BfhlService {
    BfhlResponseDto processData(BfhlRequestDto request);
}
