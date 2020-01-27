package dev.hotel.controler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;

//REST
// Web API
@RestController
@RequestMapping("hotel")
public class HotelCtrl {

	@RequestMapping(method = RequestMethod.GET, path = "")
	// @ResponseBody
	public Client CreerUnHotel() {
		return new Client("Hugues", "Jules");
	}

	@RequestMapping(method = RequestMethod.GET, path = "client")
	// @ResponseBody
	public Client CreerUnClient() {
		return new Client("Hugues", "Jules");
	}
}
