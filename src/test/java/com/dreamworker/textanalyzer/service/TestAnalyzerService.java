package com.dreamworker.textanalyzer.service;

import com.dreamworker.textanalyzer.dto.RequestBodyDTO;
import com.dreamworker.textanalyzer.dto.ResponseBodyDTO;
import com.dreamworker.textanalyzer.dto.TextLengthDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TestAnalyzerService {

    @InjectMocks
    AnalyzerServiceImpl analyzerService;

    ResponseBodyDTO response;

    RequestBodyDTO request;

    TextLengthDTO textLengthDTO;

    Map<Character, Integer> characterCount;

    List<Map<Character, Integer>> charCountArray;

    @Before
    public void setUp(){

        charCountArray = new ArrayList<>();
        response = new ResponseBodyDTO();
        request = new RequestBodyDTO();
        textLengthDTO = new TextLengthDTO();
        characterCount = new TreeMap<>();

        textLengthDTO.setWithSpaces(15);
        textLengthDTO.setWithoutSpaces(11);

        characterCount.put('e', 2);
        characterCount.put('h', 1);
        characterCount.put('i', 1);
        characterCount.put('l', 2);
        characterCount.put('m', 1);
        characterCount.put('o', 1);
        characterCount.put('s', 1);
        characterCount.put('t', 1);

        for (Map.Entry<Character, Integer> entry: characterCount.entrySet()){
            Map<Character, Integer> entryMap = new HashMap<>();
            entryMap.put(entry.getKey(), entry.getValue());
            charCountArray.add(entryMap);
        }

        response.setTextLength(textLengthDTO);
        response.setCharacterCount(charCountArray);
        response.setWordCount(3);

    }

    @Test
    public void testAnalyzer(){

        ResponseEntity<ResponseBodyDTO> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        request.setText("hello 2 times  ");
        Assert.assertEquals(responseEntity, analyzerService.analyzeText(request));

    }

    @Test
    public void testAnalyzerAddingSpecialCharacters(){

        //Adding special characters should not affect the characterCount list.
        request.setText("hello 2 times  ((()/%$#!)))");
        response.setWordCount(3);
        response.getTextLength().setWithSpaces(27);
        response.getTextLength().setWithoutSpaces(23);
        ResponseEntity<ResponseBodyDTO> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);

        Assert.assertEquals(responseEntity, analyzerService.analyzeText(request));

    }


}
