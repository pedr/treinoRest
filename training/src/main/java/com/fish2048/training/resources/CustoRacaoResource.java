package com.fish2048.training.resources;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.fish2048.training.domain.CustoRacao;
import com.fish2048.training.services.CustoRacaoService;

/**
 * 
 * 
 */
@RestController
@RequestMapping(value = "/custoracao")
public class CustoRacaoResource {

	@Autowired
	private CustoRacaoService custoRacaoService;
	
	@GetMapping
	public ResponseEntity<List<CustoRacao>> findAll() {
		List<CustoRacao> obj = custoRacaoService.findAll();
		return ResponseEntity.ok().body(obj);	
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CustoRacao> find(@PathVariable Integer id) {
		CustoRacao obj = custoRacaoService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody CustoRacao obj) {
		obj = custoRacaoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody CustoRacao obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = custoRacaoService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		custoRacaoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
