package cat.institutmarianao.service;

public interface MovimentStockService {
	void processMovimentStock(String medicamentId, long quantity, int signe);
}