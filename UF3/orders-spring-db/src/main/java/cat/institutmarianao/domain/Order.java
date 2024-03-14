package cat.institutmarianao.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

//TODO Put JPA and Validation annotations
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String[] STATES = { "order.state.pending", "order.state.transit", "order.state.delivery",
			"order.state.absent", "order.state.pending.collection", "order.state.returned" };

	private static int referenceSequence = 1;

	private Long reference;

	private User client;

	private Address deliveryAddress;

	private Date startDate;
	private Date deliveryDate;

	private Integer state = 0;

	private Integer totalQuantity = 0;
	private Double totalAmount = 0d;

	public static int incReferenceSequence() {
		return referenceSequence++;
	}

	public Long getReference() {
		return reference;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reference);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object instanceof Order order) {
			return Objects.equals(reference, order.reference);
		}
		return false;
	}

}
