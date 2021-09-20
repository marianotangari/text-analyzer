package com.dreamworker.textanalyzer.service;

import com.dreamworker.textanalyzer.dto.RequestBodyDTO;
import com.dreamworker.textanalyzer.dto.ResponseBodyDTO;
import org.springframework.http.ResponseEntity;

public interface AnalyzerService {

    ResponseEntity<Object> analyzeText(RequestBodyDTO req);

}
