package com.generation.farmasys.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProdutoUpdateDTO {
	
	@NotNull(message = "O atributo ID é obrigatório")
	@Schema(description = "Id da produto", example = "1")
	private Long id;

	@NotBlank(message = "O atributo nome do produto é obrigatório")
	@Size(min = 5, message = "O atributo nome deve conter no mínimo 05 caracteres")
	@Schema(description = "Nome do produto", example = "azitromicina")
	private String nomeDoProduto;
	
	@NotNull(message = "Valor não deve ser nulo")
	@Positive(message = "valor deve ser Maior que zero")
	@Digits(integer = 4, fraction = 2, message = "O preço esperado deve conter até 4 digitos inteiros com 2 casas decimais")
	@Schema(description = "Preço do produto", example = "49.50")
	private BigDecimal price;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private CategoriaIdDTO categoria;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDoProduto() {
		return nomeDoProduto;
	}

	public void setNomeDoProduto(String nomeDoProduto) {
		this.nomeDoProduto = nomeDoProduto;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public CategoriaIdDTO getCategoria() {
		return categoria;
	}
	
}
