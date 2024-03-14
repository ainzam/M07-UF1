package cat.institutmarianao.utils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.jboss.logging.Logger;

import cat.institutmarianao.domain.Address;
import cat.institutmarianao.domain.Item;
import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.OrderItem;
import cat.institutmarianao.domain.User;

public class Mock {

	public static User createUser(String username) {
		String first = username.substring(0, 1).toUpperCase() + username.substring(1);
		String last = first + "son";
		return createUser(username, username + "#pass", username + "#role", first, last);
	}

	public static User createUser(String username, String password, String role, String first, String last) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(role);
		user.setFirstName(first);
		user.setLastName(last);
		user.setOrders(new ArrayList<>());
		user.getOrders().add(createOrder(user));
		return user;
	}

	public static Order createOrder(User client) {
		Order order = new Order();
		order.setClient(client);
		order.setDeliveryAddress(createAddress(client));
		order.setStartDate(new Date(new Random().nextLong()));
		order.setDeliveryDate(new Date(new Random().nextLong(order.getStartDate().getTime(), Long.MAX_VALUE)));
		return order;
	}

	public static Item createItem() {
		Item item = new Item();

		String name = createRandomString(Item.MAX_NAME);

		item.setName("item" + new String(name));
		item.setDescription(item.getName() + " long description");
		item.setPrice(new Random().nextDouble(10, 1000));
		item.setImage(getRandomImage());
		return item;
	}

	public static OrderItem createOrderItem(Order order, Item item) {

		OrderItem orderItem = new OrderItem();

		orderItem.setOrder(order);
		orderItem.setItem(item);

		orderItem.setQuantity(new Random().nextInt(1, Integer.MAX_VALUE));
		return orderItem;
	}

	private static Address createAddress(User client) {
		Address address = new Address();
		address.setAddress(client.getUsername() + " street");
		address.setZipCode(client.getUsername() + " postal");

		address.setCity(client.getUsername() + " city");
		address.setState(client.getUsername() + " state");
		address.setCountry(client.getUsername() + " country");
		return address;
	}

	public static String createRandomString(int length) {
		// choose a Character random from this String
		final String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (allowedChars.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(allowedChars.charAt(index));
		}

		return sb.toString();
	}

	private static byte[] getRandomImage() {
		File image = null;
		try {
			URL imagesUrl = Mock.class.getClassLoader().getResource("images");
			URI imagesUri = imagesUrl.toURI();
			File imagesDir = new File(imagesUri);

			if (imagesDir.exists() && imagesDir.isDirectory() && imagesDir.canRead()) {
				image = imagesDir.listFiles()[new Random().nextInt(imagesDir.listFiles().length)];
				return Files.readAllBytes(Paths.get(image.toURI()));
			}
		} catch (URISyntaxException | IOException e) {
			Logger.getLogger(Mock.class).error("Can not read " + image);
			e.printStackTrace();
		}

		return null;
	}

}
