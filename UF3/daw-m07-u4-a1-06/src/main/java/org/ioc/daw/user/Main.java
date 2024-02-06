package org.ioc.daw.user;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.ioc.daw.db.DBConnection;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String connectionFile = "db.properties";
        UserDAO userDAO = new UserDAO(new DBConnection(connectionFile));

        while (true) {
		    // Menú de opciones
		    System.out.println("Menú:");
		    System.out.println("1. Llistat d'usuaris actius");
		    System.out.println("2. Consulta d'usuari");
		    System.out.println("3. Alta d'usuari");
		    System.out.println("4. Baixa d'usuari");
		    System.out.println("5. Sortir");

		    int opcion = scanner.nextInt();

		    switch (opcion) {
		        case 1:
		            // Llistat d'usuaris actius
		            try {
		                List<User> activeUsers = userDAO.findActiveUsers();
		                displayUsers(activeUsers);
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		            break;
		        case 2:
		            // Consulta d'usuari
		            try {
		                searchUser(userDAO, scanner);
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		            break;
		        case 3:
		            // Alta d'usuari
		            try {
		                createUser(userDAO, scanner);
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		            break;
		        case 4:
		            // Baixa d'usuari
		            try {
		                deleteUser(userDAO, scanner);
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		            break;
		        case 5:
		            // Sortir
		            System.out.println("Programa finalitzat.");
		            System.exit(0);
		        default:
		            System.out.println("Opció no vàlida");
		            break;
		    }
		}
    }

    private static void displayUsers(List<User> users) {
        for (User user : users) {
            displayUser(user);
        }
    }

    private static void displayUser(User user) {
        if (user != null) {
            System.out.println("Usuari: " + user.getUsername());
            System.out.println("Nom: " + user.getName());
            System.out.println("E-mail: " + user.getEmail());
            System.out.println("Ranking: " + user.getRank());
            System.out.println("Creat el: " + user.getCreatedOn());
            System.out.println("Actiu: " + user.isActive());
            System.out.println("Contrasenya: " + user.getPassword());  // Afegeix aquesta línia
            System.out.println("---------------");
        } else {
            System.out.println("Usuari no trobat");
        }
    }

    private static void searchUser(UserDAO userDAO, Scanner scanner) {
        System.out.println("Selecciona opció de cerca:");
        System.out.println("1. Cerca per e-mail");
        System.out.println("2. Cerca per nom d'usuari");
        int searchOption = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        if (searchOption == 1) {
            System.out.println("Introdueix l'e-mail de l'usuari:");
            String userEmail = scanner.nextLine();
            try {
                User userByEmail = userDAO.findUserByEmail(userEmail);
                displayUser(userByEmail);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (searchOption == 2) {
            System.out.println("Introdueix el nom d'usuari:");
            String username = scanner.nextLine();
            try {
                User userByUsername = userDAO.findUserByUsername(username);
                displayUser(userByUsername);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Opció no vàlida");
        }
    }

    private static void createUser(UserDAO userDAO, Scanner scanner) {
        System.out.println("Introdueix les dades de l'usuari:");
        System.out.print("Nom d'usuari: ");
        String newUsername = scanner.next();
        System.out.print("Nom: ");
        String newName = scanner.next();
        System.out.print("E-mail: ");
        String newEmail = scanner.next();
        System.out.print("Contrasenya: ");
        String newPassword = scanner.next();
        try {
            User newUser = userDAO.createUser(newUsername, newName, newEmail, newPassword);
            displayUser(newUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteUser(UserDAO userDAO, Scanner scanner) {
        System.out.println("Selecciona opció de cerca per baixa:");
        System.out.println("1. Cerca per e-mail");
        System.out.println("2. Cerca per nom d'usuari");
        int deleteOption = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        if (deleteOption == 1) {
            System.out.println("Introdueix l'e-mail de l'usuari a donar de baixa:");
            String userEmailToDelete = scanner.nextLine();
            try {
                User userToDelete = userDAO.findUserByEmail(userEmailToDelete);
                confirmAndDeleteUser(userDAO, userToDelete);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (deleteOption == 2) {
            System.out.println("Introdueix el nom d'usuari a donar de baixa:");
            String usernameToDelete = scanner.nextLine();
            try {
                User userToDelete = userDAO.findUserByUsername(usernameToDelete);
                confirmAndDeleteUser(userDAO, userToDelete);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Opció no vàlida");
        }
    }

    private static void confirmAndDeleteUser(UserDAO userDAO, User userToDelete) {
        displayUser(userToDelete);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Desitja esborrar l'usuari? (S/N)");
        String confirmation = scanner.next();

        if (confirmation.equalsIgnoreCase("S")) {
            try {
                userDAO.deactivateUser(userToDelete);
                System.out.println("Usuari desactivat amb èxit.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Esborrat cancel·lat.");
        }
    }
}
