package repository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topico, Long>{
    boolean existsByTitulo(String titulo);

    boolean existsByMensagem(String mensagem);

    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :cursoNome AND FUNCTION('YEAR', t.data_criacao) = :ano")
    Page<Topico> findByCursoNomeAndAno(String cursoNome, int ano, Pageable pageable);

    @Query("SELECT t FROM Topico t ORDER BY t.data_criacao ASC")
    Page<Topico> findAllByOrderByDataCriacaoAsc(Pageable pageable);

    boolean existsByTituloAndMensagemAndCursoId(String titulo, String mensagem, Long id);

    Page<Topico> findByStatusTrue(Pageable pageable);

    Optional<Topico> findByIdAndStatusTrue(Long id);
}
