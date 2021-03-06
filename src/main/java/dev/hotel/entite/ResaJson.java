package dev.hotel.entite;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sun.istack.NotNull;

public class ResaJson extends BaseEntite {

	@NotNull
	private LocalDate dateDebut;
	@NotNull
	private LocalDate dateFin;
	@NotNull
	private UUID clientId;
	@NotNull
	private List<UUID> chambres = new ArrayList<>();

	public ResaJson() { // Obligatoire avec JSON
	}

	public ResaJson(LocalDate dateDebut, LocalDate dateFin, UUID clientId, List<UUID> chambres) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.clientId = clientId;
		this.chambres = chambres;
	}

	/**
	 * @return the dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut
	 *            the dateDebut to set
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin
	 *            the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the clientId
	 */
	public UUID getClientId() {
		return clientId;
	}

	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the chambres
	 */
	public List<UUID> getChambres() {
		return chambres;
	}

	/**
	 * @param chambres
	 *            the chambres to set
	 */
	public void setChambres(List<UUID> chambres) {
		this.chambres = chambres;
	}
}