package pl.coderslab.driverapp.DTO;

import lombok.Data;
import pl.coderslab.driverapp.models.Question;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
public class QuizDTO {
    @NotBlank
    private String title;
}
