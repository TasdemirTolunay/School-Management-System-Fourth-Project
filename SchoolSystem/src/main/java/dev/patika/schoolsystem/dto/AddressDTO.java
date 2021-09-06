package dev.patika.schoolsystem.dto;

import dev.patika.schoolsystem.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * An Address DTO class that will return as the request and response object of the address class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO extends BaseEntity {

    /**
     * Variables to display as request and response body.
     */
    @ApiModelProperty(hidden = true)
    private long id;

    @NotBlank(message = "Country is mandatory")
    @ApiModelProperty(example = "Turkiye")
    private String country;

    @ApiModelProperty(example = "Ankara")
    @NotBlank(message = "City is mandatory")
    private String city;

    @ApiModelProperty(example = "06")
    @NotBlank(message = "Plate code is mandatory")
    private String plateCode;

}
