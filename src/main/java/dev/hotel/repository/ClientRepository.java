package dev.hotel.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hotel.entite.Client;

public interface ClientRepository extends JpaRepository<Client, UUID> {

	public List<Client> findByNom(String nom); // select * from client where
												// nom=?? d'ou FindBy +'Nom'

	public Boolean existsByNomAndPrenoms(String nom, String prenom);

}
