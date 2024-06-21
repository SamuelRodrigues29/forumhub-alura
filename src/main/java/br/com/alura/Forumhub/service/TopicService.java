package br.com.alura.Forumhub.service;

import br.com.alura.Forumhub.domain.Course;
import br.com.alura.Forumhub.domain.Topic;
import br.com.alura.Forumhub.domain.User;
import br.com.alura.Forumhub.dto.request.TopicDto;
import br.com.alura.Forumhub.dto.response.*;
import br.com.alura.Forumhub.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TopicService {
    @Autowired
    private TopicRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Transactional
    public Long saveTopic(TopicDto topicDto, String loggedUserEmail) {
        if (topicDto.title() == null || topicDto.message() == null) {
            throw new IllegalArgumentException("Título e Mensagem são obrigatórios.");
        }

        // Find the logged-in user
        User loggedUser = userService.findByEmail(loggedUserEmail);

        // Check if titulo, mensagem, and cursoId combination already exists
        if (repository.existsByTituloAndMensagemAndCursoId(topicDto.title(), topicDto.message(), topicDto.course().id())) {
            throw new IllegalArgumentException("Combinação de Título, Mensagem e Curso já existe.");
        }

        // Check if cursoId is valid (non-null and exists)
        Long cursoId = topicDto.course().id();
        if (cursoId == null || !courseService.existsById() {
            throw new IllegalArgumentException("O ID do Curso não é válido");
        }

        // Create and save the Topico entity
        Topic topico = new Topic();
        topico.setTitle(topicDto.title());
        topico.setMessage(topicDto.message());
        topico.setAuthor(loggedUser);
        topico.setCourse(new Course(cursoId, topicDto.course().name(), topicDto.course().category()));
        topico.setDataCreation(LocalDateTime.now());
        topico.setStatus(true);

        Topic savedTopico = repository.save(topico);
        return savedTopico.getId();
    }

    public Page<TopicListDto> getAllTopicosOrderByDataCriacao(Pageable pageable, String cursoNome, Integer ano) {
        Page<Topic> topicosPage;

        if (cursoNome != null && ano != null) {
            topicosPage = repository.findByCursoNomeAndAno(cursoNome, ano, pageable);
        } else {
            topicosPage = repository.findAllByOrderByDataCriacaoAsc(pageable);
        }

        return topicosPage.map(topico -> new TopicListDto(
                topico.getId(),
                topico.getTitle(),
                topico.getMessage(),
                new UserIdDto(topico.getAuthor().getId(), topico.getAuthor().getName(), topico.getAuthor().getEmail()),
                new CourseIdDto(topico.getCourse().getId(), topico.getCourse().getName(), topico.getCourse().getCategory()),
                topico.isStatus()
        ));
    }


    public void updateUser(Long topicoId, TopicDetailsDto topicoInfo) {
        Optional<Topic> optionalTopico = repository.findById(topicoId);
        if (optionalTopico.isEmpty()) {
            throw new IllegalStateException("Tópico não encontrado");
        }

        Topic existingTopico = optionalTopico.get();

        existingTopico.setTitle(topicoInfo.title());
        existingTopico.setMessage(topicoInfo.message());

        try {
            repository.save(existingTopico);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Título ou mensagem não podem ser nulos", e);
        }
    }

    @Transactional
    public void inactivateTopico(Long id) {
        // Find the topic by its ID
        Topic topic = repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Tópico não encontrado"));

        // Set the status to false
        topic.setStatus(false);

        // Save the updated topic
        repository.save(topic);
    }


    public Page<TopicListActiveDto> getAllTopicosAtivos(Pageable pageable, String cursoNome, Integer ano) {
        Page<Topic> topicosPage;

        // Only filter by status true
        topicosPage = repository.findByStatusTrue(pageable);

        return topicosPage.map(topico -> new TopicListActiveDto(
                topico.getId(),
                topico.getTitle(),
                topico.getMessage(),
                new UserIdDto(topico.getAuthor().getId(), topico.getAuthor().getName(), topico.getAuthor().getEmail()),
                new CourseIdDto(topico.getCourse().getId(), topico.getCourse().getName(), topico.getCourse().getCategoria())
        ));
    }
    public Topic findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado"));
    }

    public Optional<TopicDetailsDto> detalharTopico(Long id) {
        Optional<Topic> optionalTopico = repository.findByIdAndStatusTrue(id);
        return optionalTopico.map(this::mapToDetalhamentoDto);
    }

    private TopicDetailsDto mapToDetalhamentoDto(Topic topic) {
        List<ResponseIdDto> responseIdDtos = topic.getResponse().stream()
                .map(response -> new ResponseIdDto(
                        response.getId(),
                        response.getMessage(),
                        response.getDataCreation(),
                        response.Solution(),
                        response.getAuthor().getId(),
                        response.getTopic().getId()
                ))
                .collect(Collectors.toList());

        return new TopicDetailsDto(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                new UserIdDto(topic.getAuthor().getId(), topic.getAuthor().getName(), topic.getAuthor().getEmail()),
                new CourseIdDto(topic.getCourse().getId(), topic.getCourse().getName(), topic.getCourse().getCategory()),
                responseDto(),
                topic.isStatus()
        );
    }

    public void saveTopic(TopicDto topicDto, String loggedUserEmail) {

    }
}
