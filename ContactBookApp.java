import java.util.*;

public class ContactBookApp {

    public static void menu() {
        System.out.println("\n====== CONTACT BOOK ======");
        System.out.println("1. Add Contact");
        System.out.println("2. Display Contacts");
        System.out.println("3. Smart Search");
        System.out.println("4. Search By Number");
        System.out.println("5. Update Contact");
        System.out.println("6. Delete Contact");
        System.out.println("7. Delete All");
        System.out.println("8. Toggle Favourite");
        System.out.println("9. Show Favourites");
        System.out.println("10. Sort A-Z");
        System.out.println("11. Sort Z-A");
        System.out.println("12. Count Contacts");
        System.out.println("13. Exit");
        System.out.print("Enter choice: ");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactBook book = new ContactBook();
        int choice;

        do {  
            menu();
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: System.out.print("Name: "); String name = sc.nextLine(); System.out.print("Number: "); String number = sc.nextLine(); book.addContact(name, number); break;
                case 2: book.displayContacts(); break;
                case 3: System.out.print("Search: "); book.searchByName(sc.nextLine()); break;
                case 4: System.out.print("Number: "); book.searchByNumber(sc.nextLine()); break;
                case 5: System.out.print("Old Name: "); String oldName = sc.nextLine(); System.out.print("New Name: "); String newName = sc.nextLine(); System.out.print("New Number: "); String newNumber = sc.nextLine(); book.updateContact(oldName, newName, newNumber); break;
                case 6: System.out.print("Delete Name: "); book.deleteContact(sc.nextLine()); break;
                case 7: book.deleteAll(); break;
                case 8: System.out.print("Name: "); book.toggleFavourite(sc.nextLine()); break;
                case 9: book.showFavourites(); break;
                case 10: book.sortAZ(); break;
                case 11: book.sortZA(); break;
                case 12: book.count(); break;
                case 13: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 13);

        sc.close();
    }
}