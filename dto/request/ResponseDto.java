package dto.request;

import java.time.LocalDateTime;

public record ResponseDto(String mensagem,
                          boolean solucao,
                          Long autorId,
                          Long topicoId

) {
    public Resposta toEntity(Usuario autor, Topico topico, LocalDateTime dataCriacao) {
        Resposta resposta = new Resposta();
        resposta.setMensagem(this.mensagem());
        resposta.setSolucao(this.solucao());
        resposta.setAutor(autor);
        resposta.setTopico(topico);
        resposta.setData_criacao(dataCriacao);
        return resposta;
    }
}


