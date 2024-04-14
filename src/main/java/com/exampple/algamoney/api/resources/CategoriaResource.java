package com.exampple.algamoney.api.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.exampple.algamoney.api.model.Categoria;

import com.exampple.algamoney.api.repository.CategoriaRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.unprocessableEntity;


@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @GetMapping
    public List<Categoria> listar() {
       return categoriaRepository.findAll();
    }
    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED) não necessaria pois já tem no return
    public ResponseEntity<Categoria>criar(@RequestBody Categoria categoria, HttpServletResponse response) {
       Categoria categoriaSalva =  categoriaRepository.save(categoria);

                URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(categoriaSalva.getCodigo()).toUri();
        response.setHeader("Location", uri.toASCIIString());
        /*esse trecho de código está construindo a URI para o recurso recém-criado
        (provavelmente uma categoria), e em seguida, definindo o cabeçalho "Location"
         na resposta HTTP com a URI desse recurso, indicando onde o cliente pode
         encontrar as informações recém-criadas. Isso é útil em operações RESTful
         para que o cliente saiba onde encontrar o recurso que acabou de ser criado.*/
        return ResponseEntity.created(uri).body(categoriaSalva);

    }
    @GetMapping("/{codigo}")
    public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(codigo);
        if (categoriaOptional.isPresent()) {
            return ResponseEntity.ok(categoriaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Categoria> excluirPeloCodigo(@PathVariable Long codigo) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(codigo);
        if (categoriaOptional.isPresent()) {
            categoriaRepository.deleteById(codigo);
            return ResponseEntity.noContent().build();//status 204 No Content se a exclusão for bem-sucedida.
        } else {
            return ResponseEntity.notFound().build();//status 404 Not Found se a categoria não existir.
        }
    }
}



