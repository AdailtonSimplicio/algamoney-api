package com.exampple.algamoney.api.resources;

import java.util.List;
import com.exampple.algamoney.api.model.Categoria;
import com.exampple.algamoney.api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @GetMapping
    public List<Categoria> listar() {
       return categoriaRepository.findAll();
    }
}
