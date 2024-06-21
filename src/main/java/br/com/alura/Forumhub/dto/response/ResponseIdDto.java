package br.com.alura.Forumhub.dto.response;

import java.time.LocalDateTime;

public record ResponseIdDto(Long id,
                          String message,
                          LocalDateTime datacreation,
                          boolean solution,
                          Long authorId,
                          Long topicId) {
}
