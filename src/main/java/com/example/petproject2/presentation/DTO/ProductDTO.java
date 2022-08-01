package com.example.petproject2.presentation.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    @JsonProperty("uniqId")
    private String productId;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CustomerDTO> customers = new ArrayList<>();
}
