package cat.institutmarianao.repository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import cat.institutmarianao.domain.Item;
import cat.institutmarianao.utils.Mock;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:/dao-test-context.xml" })
class ItemDaoTest {
	@Autowired
	private ItemDao itemDao;

	@Test
	@Transactional
	void saveAndGetOk() {
		/* Setup */
		Item item = Mock.createItem();

		/* Item does not exists in database */
		assertNull(item.getReference());
		assertEquals(0, itemDao.getAll().size());

		/* Test methods */
		itemDao.save(item);
		Item itemFromDb = itemDao.get(item.getReference());

		/* Verification - item exists and has the same itemname */
		assertNotNull(itemFromDb);
		assertSame(item, itemFromDb);

	}

	@Test
	@Transactional
	void findAllOk() {
		/* Setup */
		Item[] items = { Mock.createItem(), Mock.createItem(), Mock.createItem(), Mock.createItem() };

		/* Item does not exists in database */
		for (Item item : items) {
			assertNull(item.getReference());
		}
		/* Test method - empty */
		assertEquals(0, itemDao.getAll().size());

		/* Setup */
		for (Item item : items) {
			itemDao.save(item);
		}

		/* Test method - retrieve all */
		Item[] itemsFromDb = itemDao.getAll().toArray(new Item[items.length]);

		/* Items are all in the database */
		assertEquals(items.length, itemDao.getAll().size());

		/* Verification - check all items */
		assertArrayEquals(items, itemsFromDb);

	}
}
