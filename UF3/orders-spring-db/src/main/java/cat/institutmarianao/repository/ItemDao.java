package cat.institutmarianao.repository;

import java.util.List;

import cat.institutmarianao.domain.Item;

public interface ItemDao {
	Item get(Long reference);

	List<Item> getAll();

	void save(Item item);
}
