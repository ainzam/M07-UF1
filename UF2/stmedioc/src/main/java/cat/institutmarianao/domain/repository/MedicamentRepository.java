package cat.institutmarianao.domain.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cat.institutmarianao.domain.Medicament;

public interface MedicamentRepository {
	List<Medicament> getAllMedicaments();
	Medicament getMedicamentById(String medicamentId);
	List<Medicament> getMedicamentsByCategory(String category);
	Set<Medicament> getMedicamentsByFilter(Map<String, List<String>> filterParams);
	 void addMedicament(Medicament medicament);
}