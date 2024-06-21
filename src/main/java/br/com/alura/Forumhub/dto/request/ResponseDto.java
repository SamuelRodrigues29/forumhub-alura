package br.com.alura.Forumhub.dto.request;

import br.com.alura.Forumhub.domain.Response;
import br.com.alura.Forumhub.domain.Topic;
import br.com.alura.Forumhub.domain.User;

import java.time.LocalDateTime;

public record ResponseDto(String message,
                          boolean solution,
                          Long authorId,
                          Long topicId

) {
    public Response toEntity(User author, Topic topic, LocalDateTime dataCreation) {
        Response response = new Response();
        response.setMessage(this.message());
        response.setSolution(this.solution());
        response.setAuthor(author);
        response.setTopic(topic);
        response.setDataCreation(dataCreation);
        return response;
    }
}


