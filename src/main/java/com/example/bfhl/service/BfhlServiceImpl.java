package com.example.bfhl.service;

import com.example.bfhl.dto.BfhlRequestDto;
import com.example.bfhl.dto.BfhlResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Value("${bfhl.user.id}")
    private String userId;

    @Value("${bfhl.user.email}")
    private String email;

    @Value("${bfhl.user.rollNumber}")
    private String rollNumber;

    @Override
    public BfhlResponseDto processData(BfhlRequestDto request) {
        BfhlResponseDto response = new BfhlResponseDto();
        response.setUserId(userId);
        response.setEmail(email);
        response.setRollNumber(rollNumber);

        if (request == null || request.getData() == null) {
            response.setSuccess(false);
            response.setOddNumbers(new ArrayList<>());
            response.setEvenNumbers(new ArrayList<>());
            response.setAlphabets(new ArrayList<>());
            response.setSpecialCharacters(new ArrayList<>());
            response.setSum("0");
            response.setConcatString("");
            return response;
        }

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        BigInteger totalSum = BigInteger.ZERO;
        StringBuilder allAlphabets = new StringBuilder();

        for (String element : request.getData()) {
            if (element == null) {
                continue;
            }

            // Extract all alphabetic characters from the element for concat_string
            for (char ch : element.toCharArray()) {
                if (Character.isLetter(ch)) {
                    allAlphabets.append(ch);
                }
            }

            // Classification of the element itself
            if (element.matches("^-?\\d+$")) {
                try {
                    BigInteger val = new BigInteger(element);
                    totalSum = totalSum.add(val);
                    if (!val.testBit(0)) {
                        evenNumbers.add(element);
                    } else {
                        oddNumbers.add(element);
                    }
                } catch (NumberFormatException e) {
                    specialCharacters.add(element);
                }
            } else if (element.matches("^[a-zA-Z]+$")) {
                alphabets.add(element.toUpperCase());
            } else {
                specialCharacters.add(element);
            }
        }

        // Compute concat_string: reversed and alternating caps
        String reversed = allAlphabets.reverse().toString();
        StringBuilder alternatingCaps = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char ch = reversed.charAt(i);
            if (i % 2 == 0) {
                alternatingCaps.append(Character.toUpperCase(ch));
            } else {
                alternatingCaps.append(Character.toLowerCase(ch));
            }
        }

        response.setSuccess(true);
        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialCharacters);
        response.setSum(totalSum.toString());
        response.setConcatString(alternatingCaps.toString());

        return response;
    }
}
