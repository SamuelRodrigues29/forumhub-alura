package br.com.alura.Forumhub.repository;

import br.com.alura.Forumhub.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.Optional;


public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTitulo(String titulo);

    boolean existsByMensagem(String mensagem);

    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :cursoNome AND FUNCTION('YEAR', t.data_criacao) = :ano")
    Page<Topic> findByCursoNomeAndAno(String cursoNome, int ano, Pageable pageable);

    @Query("SELECT t FROM Topico t ORDER BY t.data_criacao ASC")
    Page<Topic> findAllByOrderByDataCriacaoAsc(Pageable pageable);

    boolean existsByTituloAndMensagemAndCursoId(String titulo, String mensagem, Long id);

    Page<Topic> findByStatusTrue(Pageable pageable);

    Optional<Topic> findByIdAndStatusTrue(Long id);
}
