package pl.coderslab.driverapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
@Data
public class Question extends AbstractModel{
    private String question_value;

    @JsonIgnore
    @ManyToOne
    private Quiz quiz;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;
}
