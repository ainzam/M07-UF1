package cat.institutmarianao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.institutmarianao.domain.Medicament;
import cat.institutmarianao.domain.repository.MedicamentRepository;
import cat.institutmarianao.service.MovimentStockService;

@Service
public class MovimentStockServiceImpl implements MovimentStockService {

    @Autowired
    private MedicamentRepository medicamentRepository;

    @Override
	public void processMovimentStock(String medicamentId, long quantity, int signe) {
        Medicament medicamentById = medicamentRepository.getMedicamentById(medicamentId);
        long signedQuantity = quantity * signe;

        if ((medicamentById.getStockQuantity() + signedQuantity) < 0) {
            throw new IllegalArgumentException("No hi ha prou unitats. La quantitat en estoc és: "
                    + medicamentById.getStockQuantity());
        }

        medicamentById.setStockQuantity(medicamentById.getStockQuantity() + signedQuantity);
    }
}
