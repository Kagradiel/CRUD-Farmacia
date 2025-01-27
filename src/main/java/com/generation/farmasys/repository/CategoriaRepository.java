package com.generation.farmasys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.farmasys.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	public List<Categoria> findAllByNomeDaCategoriaContainingIgnoreCase(@Param("nomeDaCategoria") String nomeDaCategoria);

	public boolean existsByNomeDaCategoriaContainingIgnoreCase(@Param("NomeDaCategoria") String nomeDaCategoria);
}
