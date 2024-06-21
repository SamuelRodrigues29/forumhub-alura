package br.com.alura.Forumhub.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity(name = "Topico")
@Table(name= "topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime datacreation;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Course course;

    @OneToMany(mappedBy = "topico")
    private List<Response> responses = new ArrayList<>();

    public boolean isStatus() {
        return true;
    }

    public void setTitle(String title) {
    }

    public void setMessage(String message) {
    }

    public void setAuthor(User loggedUser) {
    }

    public void setCourse(Course course) {
    }

    public void setDataCreation(LocalDateTime now) {
    }

    public void setStatus(boolean b) {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public User getAuthor() {
        return author;
    }

    public Course getCourse() {
        return course;
    }

    public Optional<Object> getResponse() {
        return Optional.ofNullable(responses);
    }
}
