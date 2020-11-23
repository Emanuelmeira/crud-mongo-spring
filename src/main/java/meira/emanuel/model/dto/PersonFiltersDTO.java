package meira.emanuel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonFiltersDTO {

    @NotBlank(message = "Filter 'name' is required")
    private String name;

    private Integer age;

}
