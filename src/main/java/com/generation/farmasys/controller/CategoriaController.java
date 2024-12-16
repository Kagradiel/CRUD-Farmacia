package com.generation.farmasys.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.farmasys.dto.CategoriaCreateDTO;
import com.generation.farmasys.model.Categoria;
import com.generation.farmasys.repository.CategoriaRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Categorias", description = "Operações relacionadas as categorias de medicamentos")
public class CategoriaController {

		@Autowired
		private CategoriaRepository categoriaRepository;
		
		@Operation(
			    summary = "Buscar todas as categorias",
			    description = "Este endpoint retorna uma lista de todos as categorias disponíveis no sistema em formato JSON.")
		@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Categoria>> getAll(){
			return ResponseEntity.ok(categoriaRepository.findAll());
		}
		
		@Operation(
			    summary = "Buscar categoria por ID", 
			    description = "Este endpoint retorna os detalhes de uma categoria baseado no seu ID. Se a categoria for encontrada, retorna os dados do tema em formato JSON. Caso contrário, retorna um erro 404 (não encontrado).")
		@GetMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Categoria> getById(@PathVariable Long id){
			return categoriaRepository.findById(id)
					.map(resposta -> ResponseEntity.ok(resposta))
					.orElse(ResponseEntity.notFound().build());
		}
		
		
		@Operation(
			    summary = "Buscar categorias por nome", 
			    description = "Este endpoint retorna uma lista de categorias cujos nomes contêm a palavra-chave fornecida. A busca é case insensitive.")
		@GetMapping(value = "/categoria/{categoria}" , produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Categoria>> getByTitulo(@PathVariable String categoria){
			return ResponseEntity.ok(categoriaRepository.findAllByNomeDaCategoriaContainingIgnoreCase(categoria));
		}
		
		
		@Operation(
			    summary = "Cadastrar uma nova categoria", 
			    description = "Este endpoint cria uma nova categoria no sistema. Recebe o nome da categoria e retorna a categoria criada em formato JSON.")
		@PostMapping(
		        consumes = MediaType.APPLICATION_JSON_VALUE,
		        produces = MediaType.APPLICATION_JSON_VALUE
		    )
		public ResponseEntity<Categoria> post(@Valid @RequestBody CategoriaCreateDTO categoriaCreateDTO){
				
			if (categoriaRepository.existsByNomeDaCategoriaContainingIgnoreCase(categoriaCreateDTO.getNomeDaCategoria())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria já existe!");
	        }
				
			Categoria categoria = new Categoria();
			categoria.setNomeDaCategoria(categoriaCreateDTO.getNomeDaCategoria());
				
			return ResponseEntity.status(HttpStatus.CREATED)
						.body(categoriaRepository.save(categoria));
			
		}
		
		
		@Operation(
			    summary = "Atualizar uma categoria existente", 
			    description = "Este endpoint atualiza os dados de uma categoria existente no sistema. Recebe os dados atualizados e retorna a categoria atualizada em formato JSON.")
		@PutMapping(
		        consumes = MediaType.APPLICATION_JSON_VALUE,
		        produces = MediaType.APPLICATION_JSON_VALUE
		    ) 
		public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria){	
			
			
			return categoriaRepository.findById(categoria.getId()) 
					.map(resposta -> ResponseEntity.status(HttpStatus.OK)
					.body(categoriaRepository.save(categoria)))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
				
		}
		
		@Operation(
			    summary = "Deletar uma categoria", 
			    description = "Este endpoint remove uma categoria do sistema pelo seu ID. Se a categoria for encontrada, ela será deletada, caso contrário, retorna um erro 404 (não encontrado).")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		@DeleteMapping("/{id}")
		public void delete(@PathVariable Long id) {
			Optional<Categoria> categoria = categoriaRepository.findById(id);
			
			if(categoria.isEmpty())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
			categoriaRepository.deleteById(id);	
		}
	
}
