package pl.coderslab.driverapp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "answers")
@Data
public class Answer extends AbstractModel {

    private String answer_value;

    private Boolean isCorrect;

    @JsonIgnore
    @ManyToOne
    private Question question;
}
