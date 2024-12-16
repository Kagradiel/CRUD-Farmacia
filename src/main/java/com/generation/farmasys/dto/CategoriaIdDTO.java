package com.generation.farmasys.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class CategoriaIdDTO {

	@NotNull(message = "O atributo Id é obrigatório")
    @Schema(description = "Id da categoria para GET", example = "5")
	private Long id;

	public Long getId() {
		return id;
	}
	
}
