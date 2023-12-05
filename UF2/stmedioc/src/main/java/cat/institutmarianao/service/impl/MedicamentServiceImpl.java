package cat.institutmarianao.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.institutmarianao.domain.Medicament;
import cat.institutmarianao.domain.repository.MedicamentRepository;
import cat.institutmarianao.service.MedicamentService;

@Service
public class MedicamentServiceImpl implements MedicamentService {

	@Autowired
	private MedicamentRepository medicamentRepository;

	@Override
	public List<Medicament> getAllMedicaments() {
		return medicamentRepository.getAllMedicaments();
	}

	@Override
	public Medicament getMedicamentById(String medicamentID) {
		return medicamentRepository.getMedicamentById(medicamentID);
	}

	@Override
	public List<Medicament> getMedicamentsByCategory(String category) {
		return medicamentRepository.getMedicamentsByCategory(category);
	}

	@Override
	public Set<Medicament> getMedicamentsByFilter(Map<String, List<String>> filterParams) {
		return medicamentRepository.getMedicamentsByFilter(filterParams);
	}

	@Override
	public void addMedicament(Medicament medicament) {
		medicamentRepository.addMedicament(medicament);
	}
}
