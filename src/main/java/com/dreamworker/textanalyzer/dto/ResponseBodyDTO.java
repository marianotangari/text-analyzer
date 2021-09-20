package com.dreamworker.textanalyzer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBodyDTO {

    @JsonProperty("textLength")
    TextLengthDTO textLength;

    @JsonProperty("characterCount")
    List<Map<Character, Integer>> characterCount;

    @JsonProperty("wordCount")
    Integer wordCount;

}
