package dev.hotel.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Reservation;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.ReservationRepository;

@Service
public class ReservationService {

	private ReservationRepository reservationRepository;
	private ClientRepository clientRepository;
	private ChambreRepository chambreRepository;

	public ReservationService(ReservationRepository reservationRepository, ClientRepository clientRepository,
			ChambreRepository chambreRepository) {
		super();
		this.reservationRepository = reservationRepository;
		this.clientRepository = clientRepository;
		this.chambreRepository = chambreRepository;
	}

	public Reservation creerUneResa(LocalDate dateDebut, LocalDate dateFin, List<UUID> chambres, UUID clientId) {

		Reservation newR = new Reservation();
		List<Chambre> listChambres = new ArrayList<Chambre>(0);
		newR.setDateDebut(dateDebut);
		newR.setDateFin(dateFin);
		for (UUID u : chambres) {
			if (chambreRepository.findById(u).isPresent()) {
				Chambre ch = this.chambreRepository.findById(u).get();
				listChambres.add(ch);
			}
		}
		newR.setChambres(listChambres);
		newR.setClient(clientRepository.findById(clientId)
				.orElseThrow(() -> new EntityNotFoundException("client non trouvé")));
		newR.setChambres(chambres.stream()
				.map(chambreId -> chambreRepository.findById(chambreId)
						.orElseThrow(() -> new EntityNotFoundException("la chambre " + chambreId + " n'existe pas")))
				.collect(Collectors.toList()));
		return reservationRepository.save(newR);
	}

	public List<Reservation> listerReservations() {
		return reservationRepository.findAll();
	}
}