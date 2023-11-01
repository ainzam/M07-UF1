package cat.institutmarianao.ejb;

import javax.ejb.Local;

@Local
public interface LibraryLocal {
	
	String catalog(String book);

	String checkAvailability(String book);

	Boolean borrow(String book);
}
