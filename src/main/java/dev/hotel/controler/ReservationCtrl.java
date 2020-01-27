package dev.hotel.controler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.ResaJson;
import dev.hotel.entite.Reservation;
import dev.hotel.service.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationCtrl {

	private ReservationService reservationService;

	public ReservationCtrl(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	// POST /reservation

	@PostMapping

	public ResponseEntity<Reservation> postReservation(@RequestBody ResaJson reservationRecu) {

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(this.reservationService.creerUneResa(reservationRecu.getDateDebut(), reservationRecu.getDateFin(),
						reservationRecu.getChambres(), reservationRecu.getClientId()));
	}

	@GetMapping
	public List<Reservation> listerResa() {
		return this.reservationService.listerReservations();
	}

}