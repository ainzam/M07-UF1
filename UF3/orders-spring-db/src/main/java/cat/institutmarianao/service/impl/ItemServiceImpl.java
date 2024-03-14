package cat.institutmarianao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.institutmarianao.domain.Item;
import cat.institutmarianao.repository.ItemDao;
import cat.institutmarianao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemDao itemDao;

	@Override
	public Item get(Long reference) {
		return itemDao.get(reference);
	}

	@Override
	public List<Item> getAll() {
		return itemDao.getAll();
	}

}
