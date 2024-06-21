package br.com.alura.Forumhub.repository;

import br.com.alura.Forumhub.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course saveCourse();
}
