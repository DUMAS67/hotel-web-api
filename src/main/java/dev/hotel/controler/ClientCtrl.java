/**
 * 
 */
package dev.hotel.controler;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.hotel.service.ClientService;

/**
 * @author Stagiaire
 *
 */

@RestController
@RequestMapping("/clients")
public class ClientCtrl {

	private ClientService clientService;

	public ClientCtrl(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	// @RequestMapping(method = RequestMethod.GET)

	@GetMapping
	public List<Client> listerClients() {
		return this.clientService.listerClients();
	}

	// GET /client/?nom=XXX&prenom=XXX
	// @RequestMapping(method = RequestMethod.GET, params = "nom")

	@GetMapping(params = "nom")
	public List<Client> rechercherParNom(@RequestParam("nom") String nomRequete) {
		return this.clientService.findByNom(nomRequete);
	}

	// POST /client/creer

	@PostMapping
	public UUID creerClient(@RequestBody @Valid Client clientRecu) {
		return this.clientService.creerClient(clientRecu);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> validationException(MethodArgumentNotValidException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(value = { EntityExistsException.class })
	public ResponseEntity<String> ClientPresent(EntityExistsException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("client déja en bdd");
	}

}
