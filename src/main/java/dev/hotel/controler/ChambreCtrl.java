package dev.hotel.controler;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.repository.ChambreRepository;

@RestController
@RequestMapping("/chambre")
public class ChambreCtrl {

	private ChambreRepository chambreRepository;

	public ChambreCtrl(ChambreRepository chambreRepository) {
		super();
		this.chambreRepository = chambreRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	// @ResponseBody
	public List<Chambre> listerChambre() {

		return chambreRepository.findAll();
	}
}
