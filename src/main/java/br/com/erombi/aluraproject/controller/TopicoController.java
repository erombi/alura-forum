package br.com.erombi.aluraproject.controller;

import br.com.erombi.aluraproject.controller.dto.DetalhesTopicoDTO;
import br.com.erombi.aluraproject.controller.dto.TopicoDTO;
import br.com.erombi.aluraproject.controller.form.AtualizacaoTopicoForm;
import br.com.erombi.aluraproject.controller.form.TopicoForm;
import br.com.erombi.aluraproject.model.Topico;
import br.com.erombi.aluraproject.repository.CursoRepository;
import br.com.erombi.aluraproject.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Cacheable(value = "listaTopicos")
    @GetMapping
    public ResponseEntity<Page<TopicoDTO>> lista(@RequestParam(required = false) String nomeCurso,
                                                 @PageableDefault(sort = "id", direction = Sort.Direction.ASC,
                                                                  page = 0, size = 10) Pageable pageable) {


        if (nomeCurso != null) {
            Page<Topico> topicos = repository.findByCursoNomeIsContaining(nomeCurso, pageable).orElseThrow(() -> new RuntimeException("Error"));
            return ResponseEntity.ok(TopicoDTO.converter(topicos));
        } else {
            Page<Topico> topicos = repository.findAll(pageable);
            return ResponseEntity.ok(TopicoDTO.converter(topicos));
        }
    }

    @CacheEvict(value = "listaTopicos", allEntries = true)
    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder builder) {
        Topico topico = form.converter(cursoRepository);
        repository.save(topico);
        URI uri = builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesTopicoDTO> detalhar(@PathVariable Long id) {
        Optional<Topico> one = repository.findById(id);
        return one.isPresent() ? ResponseEntity.ok(new DetalhesTopicoDTO(one.get())) : ResponseEntity.notFound().build();
    }

    @CacheEvict(value = "listaTopicos", allEntries = true)
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
        Optional<Topico> one = repository.findById(id);
        if (one.isPresent()) {
            Topico topico = form.atualizar(id, repository);
            return ResponseEntity.ok(new TopicoDTO(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @CacheEvict(value = "listaTopicos", allEntries = true)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Topico> one = repository.findById(id);
        if (one.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
