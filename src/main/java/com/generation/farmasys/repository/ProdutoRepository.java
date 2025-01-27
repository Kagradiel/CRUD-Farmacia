package com.generation.farmasys.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.farmasys.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	public List<Produto> findAllByNomeDoProdutoContainingIgnoreCase(@Param("nomeDoProduto") String nomeDoProduto);
	
	public boolean existsByNomeDoProdutoContainingIgnoreCase(@Param("NomeDaCategoria") String nomeDaCategoria);
	
}
