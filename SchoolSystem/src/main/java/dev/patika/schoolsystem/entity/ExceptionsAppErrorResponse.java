package dev.patika.schoolsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Is the class that will log the error information in the project.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionsAppErrorResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private long id;

    private int status;
    private String message;
    private String exceptionType;
    private LocalDate exceptionThrowDate;

}
