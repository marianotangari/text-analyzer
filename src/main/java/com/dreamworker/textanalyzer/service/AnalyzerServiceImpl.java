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

        String originalText = requestBody.getText().toLowerCase();

        //This regex removes all special characters except whitespaces
        String sanitizedText = originalText.replaceAll("[^a-zA-Z0-9\\s+]","");

        //We split the sanitized text into a string array using spaces as division.
        String[] splitText = sanitizedText.trim().split("\\s+");

        //This regex removes all spaces (even consecutive ones) and numbers
        String trimmedText = sanitizedText.replaceAll("[\\d\\s+]","");

        TextLengthDTO textLengthDTO = new TextLengthDTO(originalText.length(), originalText.replaceAll("\\s+","").length());

        //We convert the string in a char array in order to sort it alphabetically.
        char[] tempArray = trimmedText.toCharArray();
        Arrays.sort(tempArray);
        trimmedText = new String(tempArray);

        //This algorithm get occurrences of every character in string and then remove it from the string.
        //This way, we don't need to iterate using a for loop and wasting steps. We always take the first character in the string input.

        while(trimmedText.length() > 0){

            Map<Character, Integer> map= new HashMap<>();
            map.put(trimmedText.charAt(0), StringUtils.countOccurrencesOf(trimmedText, String.valueOf(trimmedText.charAt(0))));
            charCountArray.add(map);
            trimmedText = trimmedText.replace(String.valueOf(trimmedText.charAt(0)), "");

        }

        ResponseBodyDTO response = new ResponseBodyDTO(textLengthDTO, originalText.length() > 0 ? splitText.length : 0, charCountArray);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
