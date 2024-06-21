package br.com.alura.Forumhub.service;

import br.com.alura.Forumhub.domain.Response;
import br.com.alura.Forumhub.domain.Topic;
import br.com.alura.Forumhub.domain.User;
import br.com.alura.Forumhub.dto.request.ResponseDto;
import br.com.alura.Forumhub.repository.ResponseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    public User findByEmail(String email) {
        return userService.findByEmail(email);
    }

    @Transactional
    public void saveResposta(Long topicId, ResponseDto respostaDto, User autor, LocalDateTime dataCriacao) {
        // Retrieve the associated Topico
        Topic topic = topicService.findById(topicId);


        if (topic.isStatus()) {

            Response resposta = responseDto.toEntity(autor, topic, dataCriacao);
            responseRepository.save(resposta);
        } else {

            throw new IllegalArgumentException("Tópico inativo. Resposta não foi salva");
        }
    }

}
