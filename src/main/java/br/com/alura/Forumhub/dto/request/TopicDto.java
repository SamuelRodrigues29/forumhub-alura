package br.com.alura.Forumhub.dto.request;

import br.com.alura.Forumhub.dto.response.CourseIdDto;
import br.com.alura.Forumhub.dto.response.UserIdDto;
import jakarta.validation.constraints.NotBlank;

public record TopicDto(@NotBlank String title,
                       @NotBlank String message,
                       UserIdDto author,
                       CourseIdDto course) {
}
