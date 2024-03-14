package cat.institutmarianao.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "items")
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAX_NAME = 50;
	public static final int MAX_DESCRIPTION = 250;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reference;

	@NotEmpty
	@Size(max = MAX_NAME)
	private String name;

	@NotEmpty
	@Size(max = MAX_DESCRIPTION)
	private String description;

	@NotNull
	private Double price;

	@NotEmpty
	@Basic(fetch = FetchType.LAZY)
	@Lob
	@Column(columnDefinition = "BLOB")
	private byte[] image;

	public Long getReference() {
		return reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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
		if (object instanceof Item item) {
			return Objects.equals(reference, item.reference);
		}
		return false;
	}

}
