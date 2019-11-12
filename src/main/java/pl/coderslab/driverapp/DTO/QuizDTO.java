package pl.coderslab.driverapp.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class QuizDTO {
    @NotBlank
    private String title;
}
