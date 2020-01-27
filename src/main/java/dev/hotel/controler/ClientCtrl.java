/**
 * 
 */
package dev.hotel.controler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

/**
 * @author Stagiaire
 *
 */

@RestController
@RequestMapping("/client")
public class ClientCtrl {

	private ClientRepository clientRepository;

	public ClientCtrl(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	// @ResponseBody
	public List<Client> listerClient() {

		return clientRepository.findAll();
	}

	// GET /client/?nom=XXX&prenom=XXX
	@RequestMapping(method = RequestMethod.GET, params = "nom")
	// @ResponseBody
	public List<Client> queryParam(@RequestParam("nom") String nomRequeteHttp) {
		return clientRepository.findByNom("PIERRE");
	}

	// POST /client/creer
	@RequestMapping(method = RequestMethod.POST, path = "creer")
	// @ResponseBody
	public ResponseEntity<String> post(@RequestBody Client nouveauClient) {

		if (clientRepository.existsByNomAndPrenoms(nouveauClient.getNom(), nouveauClient.getPrenoms())) {
			// message d'erreur + status.
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("client non trouvé : ");
		} else {
			clientRepository.save(nouveauClient);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("nouveau Client " + nouveauClient.getUuid().toString());

		}
	}

}
