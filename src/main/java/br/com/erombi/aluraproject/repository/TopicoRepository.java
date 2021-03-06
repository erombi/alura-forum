package br.com.erombi.aluraproject.repository;

import br.com.erombi.aluraproject.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Optional<Page<Topico>> findByCursoNomeIsContaining(String nomeCurso, Pageable pageable);
}
