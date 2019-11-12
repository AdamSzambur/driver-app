package pl.coderslab.driverapp.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "quizzes")
@Data
public class Quiz extends AbstractModel {

    private String title;

    @OneToMany(mappedBy = "quiz")
    private List<Question> questions;
}
