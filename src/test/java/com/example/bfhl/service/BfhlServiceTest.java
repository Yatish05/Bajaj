package com.example.bfhl.service;

import com.example.bfhl.dto.BfhlRequestDto;
import com.example.bfhl.dto.BfhlResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BfhlServiceTest {

    @Autowired
    private BfhlService bfhlService;

    @Test
    public void testExampleA() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("a", "1", "334", "4", "R", "$"));

        BfhlResponseDto response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals("yatish_bansal_06032005", response.getUserId());
        assertEquals("yatishbansal05@gmail.com", response.getEmail());
        assertEquals("2310991439", response.getRollNumber());
        assertEquals(Collections.singletonList("1"), response.getOddNumbers());
        assertEquals(Arrays.asList("334", "4"), response.getEvenNumbers());
        assertEquals(Arrays.asList("A", "R"), response.getAlphabets());
        assertEquals(Collections.singletonList("$"), response.getSpecialCharacters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcatString());
    }

    @Test
    public void testExampleB() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));

        BfhlResponseDto response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(Collections.singletonList("5"), response.getOddNumbers());
        assertEquals(Arrays.asList("2", "4", "92"), response.getEvenNumbers());
        assertEquals(Arrays.asList("A", "Y", "B"), response.getAlphabets());
        assertEquals(Arrays.asList("&", "-", "*"), response.getSpecialCharacters());
        assertEquals("103", response.getSum());
        assertEquals("ByA", response.getConcatString());
    }

    @Test
    public void testExampleC() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("A", "ABCD", "DOE"));

        BfhlResponseDto response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertEquals(Arrays.asList("A", "ABCD", "DOE"), response.getAlphabets());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("EoDdCbAa", response.getConcatString());
    }

    @Test
    public void testEmptyInput() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Collections.emptyList());

        BfhlResponseDto response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    public void testOnlyNumbers() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("1", "2", "3", "4", "10"));

        BfhlResponseDto response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(Arrays.asList("1", "3"), response.getOddNumbers());
        assertEquals(Arrays.asList("2", "4", "10"), response.getEvenNumbers());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("20", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    public void testOnlySpecialCharacters() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("@", "#", "$", "%"));

        BfhlResponseDto response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertEquals(Arrays.asList("@", "#", "$", "%"), response.getSpecialCharacters());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    public void testNullInputList() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(null);

        BfhlResponseDto response = bfhlService.processData(request);

        assertFalse(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    public void testNullRequestDto() {
        BfhlResponseDto response = bfhlService.processData(null);

        assertFalse(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    public void testVeryLargeNumbers() {
        BfhlRequestDto request = new BfhlRequestDto();
        request.setData(Arrays.asList("123456789012345678901234567890", "987654321098765432109876543211"));

        BfhlResponseDto response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(Collections.singletonList("987654321098765432109876543211"), response.getOddNumbers());
        assertEquals(Collections.singletonList("123456789012345678901234567890"), response.getEvenNumbers());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("1111111110111111111011111111101", response.getSum());
        assertEquals("", response.getConcatString());
    }
}
