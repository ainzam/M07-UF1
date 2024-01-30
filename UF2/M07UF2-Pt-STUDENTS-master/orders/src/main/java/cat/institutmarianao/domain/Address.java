package cat.institutmarianao.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Address {

	private String recipientName;

    @NotBlank(message = "Address cannot be blank")
    @Size(max = 50, message = "Address must be at most 50 characters")
	private String address;
    
    @NotBlank(message = "Zip code cannot be blank")
    @Size(max = 10, message = "Zip code must be at most 10 characters")
	private String zipCode;
    
    @NotBlank(message = "City cannot be blank")
    @Size(max = 50, message = "City must be at most 50 characters")
	private String city;
    

    @NotBlank(message = "State cannot be blank")
    @Size(max = 20, message = "State must be at most 20 characters")
	private String state;
    
    @NotBlank(message = "Country cannot be blank")
    @Size(max = 20, message = "Country must be at most 20 characters")
	private String country;

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
    @Override
    public String toString() {
        return "Street: " + address + ", City: " + city + ", Zip Code: " + zipCode + ", State: " + state; // Ajusta según tus atributos
    }
	
}
