package cat.institutmarianao.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

//TODO Put JPA and Validation annotations
@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String[] STATES = { "order.state.pending", "order.state.transit", "order.state.delivery",
			"order.state.absent", "order.state.pending.collection", "order.state.returned" };

	private static int referenceSequence = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reference;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private User client;

	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "deliveryAddress_id")
	private Address deliveryAddress;

    @NotNull
    @Column(name = "startDate")
	private Date startDate;
    
    @Column(name = "deliveryDate")
	private Date deliveryDate;

    @NotNull
	private Integer state = 0;
    
    @NotNull
    @Column(name = "totalQuantity")
	private Integer totalQuantity = 0;
    
    @NotNull
    @Column(name = "totalAmount")
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
