package br.com.alura.Forumhub.service;

import br.com.alura.Forumhub.domain.Course;
import br.com.alura.Forumhub.dto.request.CourseDto;
import br.com.alura.Forumhub.dto.response.CourseIdDto;
import br.com.alura.Forumhub.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;


    @Transactional
    public Long saveCourse(CourseDto courseDto) {
        // Validate DTO fields
        if (courseDto.name() == null || courseDto.category() == null) {
            throw new IllegalArgumentException("Nome and categoria fields are required.");
        }



        // Save curso to repository
        Course savedCurso = repository.saveCourse();
        return savedCurso.getId();
    }

    public Page<CourseIdDto> getAllCursos(Pageable pageable) {
        Page<Course> cursosPage = repository.findAll(pageable);
        return cursosPage.map(cursos -> new CourseIdDto(
                cursos.getId(), cursos.getName(),
                cursos.getCategory()));
    }


    public void updateCurso(Long cursoId,
                            CourseIdDto cursoIdDto) {
        Optional<Course> optional = repository.findById(cursoId);
        if (optional.isEmpty()) {
            throw new IllegalStateException("Curso not found with ID: " + cursoId);
        }
        Course existingCurso = optional.get();

        existingCurso.setName(cursoIdDto.name());
        existingCurso.setCategory(cursoIdDto.category());

        repository.save(existingCurso);
    }

    public boolean existsById(Long cursoId) {
        return repository.existsById(cursoId);
    }

    public Long saveCourse(CourseDto cursoDto) {
        return saveCourse(courseDto);
    }

    public boolean existsById() {
        return true;
    }
}