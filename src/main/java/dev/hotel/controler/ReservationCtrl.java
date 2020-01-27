package dev.hotel.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.UneResa;
import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.ReservationRepository;

@RestController
@RequestMapping("/reservation")
public class ReservationCtrl {

	private ReservationRepository reservationRepository;
	private ClientRepository clientRepository;
	private ChambreRepository chambreRepository;

	public ReservationCtrl(ReservationRepository reservationRepository, ClientRepository clientRepository,
			ChambreRepository chambreRepository) {
		super();
		this.reservationRepository = reservationRepository;
		this.clientRepository = clientRepository;
		this.chambreRepository = chambreRepository;
	}

	// POST /reservation/creer
	@RequestMapping(method = RequestMethod.POST)
	// @ResponseBody
	public ResponseEntity<String> creerUneResa(@RequestBody UneResa uneResa) {

		boolean bOk = false;
		Reservation newR = new Reservation();
		if (!clientRepository.existsById(uneResa.getClientId())) {
			// message d'erreur + status.
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("client non trouvé : ");
		} else {
			Client client = clientRepository.getOne(uneResa.getClientId());
			List<Chambre> listCh = chambreRepository.findAll();
			List<Chambre> listChambres = new ArrayList<Chambre>(0);
			for (UUID u : uneResa.getChambres()) {
				for (Chambre ch : listCh) {
					if ((ch.getUuid().equals(u)) && (bOk)) {
						bOk = true;
						listChambres.add(chambreRepository.getOne(u));
					} else {
						bOk = false;
					}
				}
			}
			if (!bOk) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("chambre non trouvé : ");
			} else {
				newR.setDateDebut(uneResa.getDateDebut());
				newR.setDateFin(uneResa.getDateFin());
				newR.setClient(client);
				newR.setChambres(listChambres);
				reservationRepository.save(newR);
				return ResponseEntity.status(HttpStatus.CREATED)
						.body("nouvelle réservation " + newR.getUuid().toString());
			}
		}
	}
}
