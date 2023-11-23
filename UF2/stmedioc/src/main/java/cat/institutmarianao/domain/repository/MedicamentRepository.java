package cat.institutmarianao.domain.repository;

import java.util.List;

import cat.institutmarianao.domain.Medicament;

public interface MedicamentRepository {
	List<Medicament> getAllMedicaments();
	Medicament getMedicamentById(String medicamentId);
}