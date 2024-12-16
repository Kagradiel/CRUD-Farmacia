package com.generation.farmasys.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Id do tema para GET", example = "5")
	private Long id;
	
	@NotNull(message = "Nome da categoria deve existir")
	@Size(min = 3, message = "No minimo 3 caracteres")
	@Schema(description = "categoria de medicamentos", example = "Antibiotico")
	private String nomeDaCategoria;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("categoria")
	private List<Produto> produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDaCategoria() {
		return nomeDaCategoria;
	}

	public void setNomeDaCategoria(String nomeDaCategoria) {
		this.nomeDaCategoria = nomeDaCategoria;
	}
	
	
	

}
