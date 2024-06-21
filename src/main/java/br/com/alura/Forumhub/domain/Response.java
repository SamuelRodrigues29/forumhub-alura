package br.com.alura.Forumhub.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Resposta")
@Table(name= "resposta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime dataCreation;
    private boolean solution;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topic topic;

    public void setMessage(String message) {
    }

    public void setSolution(boolean solution) {
    }

    public void setAuthor(User author) {
    }

    public void setTopic(Topic topic) {
    }

    public void setDataCreation(LocalDateTime dataCreation) {
    }
}