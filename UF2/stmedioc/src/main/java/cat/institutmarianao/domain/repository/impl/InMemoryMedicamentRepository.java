package cat.institutmarianao.domain.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cat.institutmarianao.domain.Medicament;
import cat.institutmarianao.domain.repository.MedicamentRepository;

@Repository
public class InMemoryMedicamentRepository implements MedicamentRepository {

	private List<Medicament> listOfMedicaments = new ArrayList<Medicament>();

	public InMemoryMedicamentRepository() {
		Medicament ibuprofe = new Medicament("M010", "Ibuprofé", 2);
		ibuprofe.setDescription("Ibuprofé de 600mg");
		ibuprofe.setCategory("Anti−inflamatori");
		ibuprofe.setProducer("Cinfa");
		ibuprofe.setStockQuantity(214);

		Medicament paracetamol = new Medicament("M020", "Paracetamol", 2.6);
		paracetamol.setDescription("Paracetamol 1g");
		paracetamol.setCategory("Analgèsic");
		paracetamol.setProducer("Ferrer");
		paracetamol.setStockQuantity(56);

		Medicament acacetilsalicilico = new Medicament("M030", "Ac Acetil Salicílico", 2.6);
		acacetilsalicilico.setDescription("Ac Acetil Salicílico");
		acacetilsalicilico.setCategory("Analgèsic");
		acacetilsalicilico.setProducer("Bayer");
		acacetilsalicilico.setStockQuantity(15);

		listOfMedicaments.add(ibuprofe);
		listOfMedicaments.add(paracetamol);
		listOfMedicaments.add(acacetilsalicilico);
	}

	public Medicament getMedicamentById(String medicamentId) {
		Medicament medicamentById = null;

		for (Medicament medicament : listOfMedicaments) {
			if (medicament != null && medicament.getMedicamentId() != null
					&& medicament.getMedicamentId().equals(medicamentId)) {
				medicamentById = medicament;
				break;
			}
		}

		if (medicamentById == null) {
			throw new IllegalArgumentException("No s’han trobat medicaments amb el codi: " + medicamentId);
		}

		return medicamentById;
	}

	@Override
	public List<Medicament> getAllMedicaments() {
		return listOfMedicaments;
	}
}
