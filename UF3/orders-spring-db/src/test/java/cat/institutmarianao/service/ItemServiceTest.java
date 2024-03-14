package cat.institutmarianao.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cat.institutmarianao.domain.Item;
import cat.institutmarianao.repository.ItemDao;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:/services-test-context.xml" })
class ItemServiceTest {
	@Autowired
	private ItemDao itemDao;

	@Autowired
	private ItemService itemService;

	@Test
	void getItemOk() {
		/* Setup */
		Long reference = new Random().nextLong();
		Item item = mock(Item.class);

		when(itemDao.get(reference)).thenReturn(item);

		/* Test */
		Item itemFromDb = itemService.get(reference);

		/* Verification */
		assertSame(item, itemFromDb);
		verify(itemDao, times(1)).get(reference);
	}

	@Test
	void getAllOk() {
		/* Setup */
		@SuppressWarnings("unchecked")
		List<Item> items = mock(List.class);

		when(itemDao.getAll()).thenReturn(items);

		/* Test */
		List<Item> itemsFromDb = itemService.getAll();

		/* Verification */
		assertSame(items, itemsFromDb);
		verify(itemDao, times(1)).getAll();
	}

}