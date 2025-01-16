package digitalinnovation.one.padroes_projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digitalinnovation.one.padroes_projeto.model.Cliente;
import digitalinnovation.one.padroes_projeto.service.ClienteService;

/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, 
 * pois abstrai toda a complexidade de integrações (Banco de Dados H2 e API do ViaCEP) 
 * em uma interface simples e coesa (API REST).
 */
@RestController
@RequestMapping("clientes")
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<Iterable<Cliente>> buscarTodos() {
		return ResponseEntity.ok(clienteService.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(clienteService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Object> inserir(@RequestBody Cliente cliente) {
		if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
			return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body("Erro: O nome do cliente é obrigatório.");
		}

		clienteService.inserir(cliente);
		// return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
		return ResponseEntity.ok(cliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
		if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
			return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body("Erro: O nome do cliente é obrigatório.");
		}

		clienteService.atualizar(id, cliente);
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		clienteService.deletar(id);
		return ResponseEntity.ok().build();
	}
}
