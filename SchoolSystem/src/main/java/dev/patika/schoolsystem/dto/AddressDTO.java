package dev.patika.schoolsystem.dto;

import dev.patika.schoolsystem.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO extends BaseEntity {

    @ApiModelProperty(hidden = true)
    private long id;

    @NotBlank(message = "Country is mandatory")
    private String country;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "Plate code is mandatory")
    private String plateCode;

}
