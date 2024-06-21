package br.com.alura.Forumhub.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/curso")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private service.CourseService service;

    @PostMapping("")
    @Transactional
    public ResponseEntity<String> cadastrarCurso(@RequestBody @Valid dto.request.CourseDto cursoDto,
                                                 UriComponentsBuilder uriComponentsBuilder) {

        Long cursoId = service.saveCourse(cursoDto);
        var uri = uriComponentsBuilder.path("/curso/{id}")
                .buildAndExpand(cursoId)
                .toUri();
        return ResponseEntity.created(uri)
                .body("Curso registrado com sucesso");

    }


    @GetMapping("")
    public ResponseEntity<Page<dto.response.CourseIdDto>> listarCursos(Pageable pageable) {
        Page<dto.response.CourseIdDto> cursosPage = service.getAllCursos(pageable);
        return ResponseEntity.ok(cursosPage);
    }


    @PutMapping("/{cursoId}")
    public ResponseEntity<String> atualizarCurso(
            @PathVariable Long cursoId,
            @RequestBody dto.response.CourseIdDto cursoIdDto) {

        service.updateCurso(cursoId, cursoIdDto);
        return ResponseEntity.ok("Curso atualizado com sucesso.");

    }

}