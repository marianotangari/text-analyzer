package com.dreamworker.textanalyzer.service;

import com.dreamworker.textanalyzer.dto.RequestBodyDTO;
import com.dreamworker.textanalyzer.dto.ResponseBodyDTO;
import com.dreamworker.textanalyzer.dto.TextLengthDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class AnalyzerServiceImpl implements AnalyzerService{

    public ResponseEntity<Object> analyzeText(RequestBodyDTO requestBody){

        List<Map<Character, Integer>> charCountArray = new ArrayList<>();

        String originalText = requestBody.getText();

        String[] splitText = originalText.split("\\s+");

        //This regex removes all spaces (even consecutive ones)
        String trimmedText = originalText.replaceAll("\\s+","");
        //This regex removes all special characters
        String sanitizedText = trimmedText.replaceAll("[^a-zA-Z]","");

        TextLengthDTO textLengthDTO = new TextLengthDTO(originalText.length(), trimmedText.length());

        //We convert the string in a char array in order to sort it alphabetically.
        char[] tempArray = sanitizedText.toCharArray();
        Arrays.sort(tempArray);
        sanitizedText = new String(tempArray);

        //This algorithm get occurrences of every character in string and then remove it from the string.
        //This way, we don't need to iterate using a for loop and wasting steps. We always take the first character in the string input.

        while(sanitizedText.length() > 0){

            Map<Character, Integer> map= new HashMap<>();
            map.put(sanitizedText.charAt(0), StringUtils.countOccurrencesOf(sanitizedText, String.valueOf(sanitizedText.charAt(0))));
            charCountArray.add(map);
            sanitizedText = sanitizedText.replace(String.valueOf(sanitizedText.charAt(0)), "");

        }

        ResponseBodyDTO response = new ResponseBodyDTO(textLengthDTO, charCountArray, splitText.length);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
