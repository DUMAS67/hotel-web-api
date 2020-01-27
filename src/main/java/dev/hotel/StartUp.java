package dev.hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Hotel;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.HotelRepository;

@Component

public class StartUp {

	private static final Logger LOG = LoggerFactory.getLogger(StartUp.class);
	private HotelRepository hotelRepository;
	private ClientRepository clientRepository;
	private ChambreRepository chambreRepository;

	public StartUp(HotelRepository hotelRepository, ClientRepository clientRepository,
			ChambreRepository chambreRepository) {
		super();
		this.hotelRepository = hotelRepository;
		this.clientRepository = clientRepository;
		this.chambreRepository = chambreRepository;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void init() {
		LOG.info("Démarrage de l'application");
		Hotel hotel = new Hotel();
		Hotel hotel1 = new Hotel();
		if (this.hotelRepository.count() == 0) {
			hotel.setNom("Crillon");
			hotel.setNombreEtoiles(5);
			this.hotelRepository.save(hotel);
		}

		if (this.hotelRepository.count() == 1) {
			hotel1.setNom("Continental");
			hotel1.setNombreEtoiles(4);
			this.hotelRepository.save(hotel1);
		}
		if (this.clientRepository.count() == 0) {
			Client client = new Client();
			client.setNom("PIERRE");
			client.setPrenoms("Jean");
			this.clientRepository.save(client);
		}
		if (this.clientRepository.count() == 1) {
			Client client = new Client();
			client.setNom("PAUL");
			client.setPrenoms("Valery");
			this.clientRepository.save(client);
		}

		if (this.chambreRepository.count() == 0) {
			Chambre chambre = new Chambre();
			chambre.setHotel(hotel);
			chambre.setNumero("101");
			chambre.setSurfaceEnM2(15.0F);
			this.chambreRepository.save(chambre);
		}
		if (this.chambreRepository.count() == 1) {
			Chambre chambre = new Chambre();
			chambre.setHotel(hotel1);
			chambre.setNumero("301");
			chambre.setSurfaceEnM2(18.0F);
			this.chambreRepository.save(chambre);
		}
	}
}
