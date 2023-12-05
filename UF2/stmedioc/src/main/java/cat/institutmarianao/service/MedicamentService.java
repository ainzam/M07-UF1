package cat.institutmarianao.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cat.institutmarianao.domain.Medicament;

public interface MedicamentService {

	List<Medicament> getAllMedicaments();

	Medicament getMedicamentById(String medicamentID);

	List<Medicament> getMedicamentsByCategory(String category);

	Set<Medicament> getMedicamentsByFilter(Map<String, List<String>> filterParams);

	void addMedicament(Medicament medicament);
}