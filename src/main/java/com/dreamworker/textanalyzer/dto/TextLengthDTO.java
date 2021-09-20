package com.dreamworker.textanalyzer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextLengthDTO {

    @JsonProperty("withSpaces")
    Integer withSpaces;

    @JsonProperty("withoutSpaces")
    Integer withoutSpaces;

}
