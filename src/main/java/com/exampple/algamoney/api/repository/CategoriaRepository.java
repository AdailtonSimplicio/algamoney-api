package com.exampple.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exampple.algamoney.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
