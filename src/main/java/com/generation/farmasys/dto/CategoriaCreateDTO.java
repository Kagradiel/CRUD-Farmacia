package com.generation.farmasys.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoriaCreateDTO {

	@NotNull(message = "Nome da categoria deve existir")
	@Size(min = 3, message = "No minimo 3 caracteres")
	@Schema(description = "categoria de medicamentos", example = "Antibiotico")
	private String nomeDaCategoria;

	public String getNomeDaCategoria() {
		return nomeDaCategoria;
	}

	public void setNomeDaCategoria(String nomeDaCategoria) {
		this.nomeDaCategoria = nomeDaCategoria;
	}
	
	
	
}
