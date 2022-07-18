package ru.oldjew.homeworkprocessor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "HOMEWORKS")
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    @JsonIgnore
    private User user;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "IS_DONE", nullable = false)
    private boolean isDone;

    public Homework() {
    }

    public Homework(User user, String title) {
        this.user = user;
        this.title = title;
    }

    public Homework(long id, User user, String title) {
        this.id = id;
        this.user = user;
        this.title = title;
    }

    public Homework(long id, User user, String title, boolean isDone) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.isDone = isDone;
    }
}
