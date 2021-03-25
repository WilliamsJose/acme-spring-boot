package br.com.will.acme.client.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.will.acme.client.domain.Client;
import br.com.will.acme.client.exceptions.ClientNotFound;
import br.com.will.acme.client.repository.ClientRepository;

@RestController
@RequestMapping("/api/clients")
public class ClientResource {
	
	@Autowired
	private ClientRepository clientRepository;

	@GetMapping
	public List<Client> getAllClients() {
		return this.clientRepository.findAll();
	}
	
	@PutMapping
	public void updateClient(@RequestBody Client client, @RequestParam("id") Long id) throws Exception {
		
		this.clientRepository.findById(id).orElseThrow(
				() -> new ClientNotFound(HttpStatus.NOT_FOUND, "Client not found")
		);
		
		this.clientRepository.save(
				new Client(id, client.getName(), client.getEmail(), client.getPhone())
		);
		
	}
	
	@GetMapping("/{id}")
	public Client getClientById(@PathVariable("id") Long id) {
		
		this.clientRepository.findById(id).orElseThrow(
				() -> new ClientNotFound(HttpStatus.NOT_FOUND, "Client not found")
		);
		
		return this.clientRepository.findById(id).get();
	}
	
	@PostMapping
	public void createClient(@Valid @RequestBody Client client) {
		
		this.clientRepository.save(client);
	}
	
	@DeleteMapping("/{id}")
	public void deleteClient(@PathVariable("id") Long id) {
		
		this.clientRepository.findById(id).orElseThrow(
				() -> new ClientNotFound(HttpStatus.NOT_FOUND, "Client not found")
		);
		
		this.clientRepository.deleteById(id);
	}
	
	@GetMapping("/gmail")
	public List<Client> getAllClientsWithGmail() {
		return this.clientRepository.getAllClientsWithGmail();
	}
}
