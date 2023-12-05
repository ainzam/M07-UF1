package cat.institutmarianao.domain;

public class Medicament {

	private String medicamentId;
	private String name;
	private double price;
	private String description;
	private String producer;
	private String category;
	private long stockQuantity;
	private long stockInOrder;
	public String getMedicamentId() {
		return medicamentId;
	}

	public Medicament () {
		super();
	}
	
	public void setMedicamentId(String medicamentId) {
		this.medicamentId = medicamentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private boolean active;

	public Medicament(String medicamentId, String name, double price) {
		this.medicamentId = medicamentId;
		this.name = name;
		this.price = price;
	}

	// Heu d’afegir els getters i setters de totes les propietats

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(long stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public long getStockInOrder() {
		return stockInOrder;
	}

	public void setStockInOrder(long stockInOrder) {
		this.stockInOrder = stockInOrder;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Medicament other = (Medicament) obj;
		if (medicamentId == null) {
			if (other.medicamentId != null) {
				return false;
			}
		} else if (!medicamentId.equals(other.medicamentId)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((medicamentId == null) ? 0 : medicamentId.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Medicament [codi=" + medicamentId + ", nom=" + name + "]";
	}
}