package com.dreamworker.textanalyzer.controller;

import com.dreamworker.textanalyzer.dto.ErrorDTO;
import com.dreamworker.textanalyzer.dto.RequestBodyDTO;
import com.dreamworker.textanalyzer.dto.ResponseBodyDTO;
import com.dreamworker.textanalyzer.service.AnalyzerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextAnalyzerController {

    @Autowired
    AnalyzerServiceImpl analyzerService;

    @GetMapping("/analyze")
    public ResponseEntity<Object> analyzeRequest(@RequestBody RequestBodyDTO req){

        try{

            return analyzerService.analyzeText(req);

        } catch (NullPointerException e){

            return new ResponseEntity<>(
                    new ErrorDTO(400, "Bad Request: Something is wrong with the request body"), HttpStatus.BAD_REQUEST);

        } catch (Exception e){

            return new ResponseEntity<>(
                    new ErrorDTO(500, "Internal Server Error"), HttpStatus.BAD_REQUEST);

        }
    }

}
