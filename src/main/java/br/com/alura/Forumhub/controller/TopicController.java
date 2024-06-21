package br.com.alura.Forumhub.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicController {
    @Autowired
    private service.TopicService service;

    @Autowired
    private service.ResponseService respostaService;

    @PostMapping("")
    public ResponseEntity<String> cadastrarTopico(
            @RequestBody @Valid dto.request.TopicDto topicoDto,
            UriComponentsBuilder uriComponentsBuilder,
            Authentication authentication) {

        // Get the logged-in user's email
        String loggedUserEmail = authentication.getName();

        // Save the topico using the service method with the logged-in user's email
        Long topicoId = service.saveTopic(topicoDto, loggedUserEmail);

        // Build the URI for the newly created resource
        var uri = uriComponentsBuilder.path("/topicos/{id}")
                .buildAndExpand(topicoId).toUri();

        // Return the response with the URI of the newly created resource
        return ResponseEntity.created(uri)
                .body("Tópico registrado com sucesso. Id: " + topicoId);
    }

    @GetMapping("/lista")
    public ResponseEntity<Page<dto.response.TopicListActiveDto>> listarTopicosAtivos(
            @RequestParam(required = false) String cursoNome,
            @RequestParam(required = false) Integer ano,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<dto.response.TopicListActiveDto> topicosPage = service.getAllTopicosAtivos(pageable, cursoNome, ano);
        return ResponseEntity.ok(topicosPage);
    }

    @GetMapping("listaAdmin")
    public ResponseEntity<Page<dto.response.TopicListDto>> listarTodosTopicos(
            @RequestParam(required = false) String cursoNome,
            @RequestParam(required = false) Integer ano,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<dto.response.TopicListDto> topicosPage = service.getAllTopicosOrderByDataCriacao(pageable, cursoNome, ano);
        return ResponseEntity.ok(topicosPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharTopico(@PathVariable Long id){
        Optional<dto.response.TopicDetailsDto> detalheOptional = service.detalharTopico(id);

        return detalheOptional
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{topicoId}")
    public ResponseEntity<String> atualizarTopico(
            @PathVariable Long topicoId,
            @RequestBody dto.response.TopicDetailsDto topicoInfo) {

        service.updateUser(topicoId, topicInfo);
        return ResponseEntity.ok("Tópico atualizado com sucesso.");

        //exclusão lógica
        @DeleteMapping("/{id}")
        @Transactional
        public ResponseEntity excluirTopico(@PathVariable Long id){
            service.inactivateTopico(id); // Call the method to inactivate the topic
            return ResponseEntity.noContent().build();
        }

        @PostMapping("/resposta/{topicId}")
        public ResponseEntity<?> salvarResposta(
                @PathVariable Long Long topicId;
        topicId,
                @RequestBody RespostaDto responseDto,
                Principal principal) {
            User autor = respostaService.findByEmail(principal.getName());
            LocalDateTime dataCriacao = LocalDateTime.now();
            respostaService.saveResposta(topicId, responseDto, autor, dataCriacao);
            return ResponseEntity.ok().build();
        }

    }
}
