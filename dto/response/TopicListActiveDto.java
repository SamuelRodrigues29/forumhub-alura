package dto.response;

public record TopicListActiveDto(Long id,
                                 String titulo,
                                 String mensagem,
                                 UsuarioIdDto autor,
                                 CursoIdDto curso) {

    public TopicosListAtivosDto(Long id, String titulo, String mensagem, UsuarioIdDto autor, CursoIdDto curso) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = autor;
        this.curso = curso;
    }
}
