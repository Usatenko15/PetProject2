package com.example.petproject2.presentation.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {
    private String customerId;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ProductDTO> products = new ArrayList<>();
}
