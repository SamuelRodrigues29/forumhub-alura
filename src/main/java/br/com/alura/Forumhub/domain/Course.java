package br.com.alura.Forumhub.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity(name = "Curso")
@Table(name= "curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;

    @OneToMany(mappedBy = "curso")
    private List<Topic> topicos = new ArrayList<>();

    public Course(Long id, String nome, String categoria) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Course() {

    }

    public void setName(String nome) {
    }

    public void setCategory(String categoria) {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}
