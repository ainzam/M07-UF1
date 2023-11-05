package cat.institutmarinao.alumnos;

import javax.ejb.Local;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Local
public class Student {
    @Pattern(regexp = "\\d{7,8}[0-9A-Za-z]", message = "Invalid DNI format")
    @NotBlank(message = "DNI is required")
    private String dni;

    @NotBlank(message = "Name is required")
    @Size(max = 200, message = "Name must be 200 characters or less")
    private String name;

    @NotBlank(message = "Surname is required")
    @Size(max = 200, message = "Surname must be 200 characters or less")
    private String surname;

    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Cycle is required")
    private String cycle;

    @Size(min = 1, message = "At least one module is required")
    private String[] modules;

	public Student() {
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni.toUpperCase();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String[] getModules() {
		return modules;
	}

	public void setModules(String[] modules) {
		this.modules = modules;
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
		Student other = (Student) obj;
		if (dni == null) {
			if (other.dni != null) {
				return false;
			}
		} else if (!dni.equalsIgnoreCase(other.dni)) {
			return false;
		}
		return true;
	}

}