package dto.response;

import java.time.LocalDateTime;

public record ResponseDto(Long id,
                          String mensagem,
                          LocalDateTime data_criacao,
                          boolean solucao,
                          Long autorId,
                          Long topicId) {
}
