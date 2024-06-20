package service;

import java.time.LocalDateTime;

public class ResponseService {
    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private UsuarioService usuarioService;

    public Usuario findByEmail(String email) {
        return usuarioService.findByEmail(email);
    }

    @Transactional
    public void saveResposta(Long topicId, RespostaDto respostaDto, Usuario autor, LocalDateTime dataCriacao) {
        // Retrieve the associated Topico
        Topico topico = topicoService.findById(topicId);

        // Check if the topic's status is true
        if (topico.isStatus()) {
            // Save the Resposta
            Resposta resposta = respostaDto.toEntity(autor, topico, dataCriacao);
            respostaRepository.save(resposta);
        } else {
            // Handle the case where the topic's status is false (optional)
            throw new IllegalArgumentException("Tópico inativo. Resposta não foi salva");
        }
    }

}
