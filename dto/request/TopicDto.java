package dto.request;

public record TopicDto(@NotBlank String titulo,
                       @NotBlank String mensagem,
                       UsuarioIdDto autor,
                       CursoIdDto curso) {
}
