import java.io.*;
import java.util.*;

class ContactBook {
    private ArrayList<Contact> contacts = new ArrayList<>();
    private final String FILE_NAME = "contacts.txt";

    public ContactBook() {
         loadFromFile();
    }

    private boolean isValidNumber(String number) {
        return number.matches("03\\d{9}");
    }

    private boolean isDuplicate(String name, String number) {
        for (Contact c : contacts)
            if (c.getName().equalsIgnoreCase(name) && c.getNumber().equals(number))
                return true;
        return false;
    }

    public void addContact(String name, String number) {
        if (!isValidNumber(number)) {
            System.out.println("Invalid number! Must be 11 digits starting with 03.");
            return;
        }
        if (isDuplicate(name, number)) {
            System.out.println("Duplicate contact not allowed.");
            return;
        }
        contacts.add(new Contact(name, number, false));
        saveToFile();
        System.out.println("Contact added successfully.");
    }

    public void displayContacts() {
        if (contacts.isEmpty()) { System.out.println("Contact book is empty."); return; }
        for (Contact c : contacts) c.display();
    }

    public void searchByName(String key) {
        boolean found = false;
        for (Contact c : contacts)
            if (c.getName().toLowerCase().contains(key.toLowerCase())) { c.display(); found = true; }
        if (!found) System.out.println("No matching contact found.");
    }

    public void searchByNumber(String number) {
        for (Contact c : contacts)
            if (c.getNumber().equals(number)) { c.display(); return; }
        System.out.println("Contact not found.");
    }

    public void updateContact(String oldName, String newName, String newNumber) {
        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(oldName)) {
                if (!isValidNumber(newNumber)) { System.out.println("Invalid number format."); return; }
                c.setName(newName);
                c.setNumber(newNumber);
                saveToFile();
                System.out.println("Contact updated.");
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public void deleteContact(String name) {
        Iterator<Contact> it = contacts.iterator();
        while (it.hasNext()) {
            Contact c = it.next();
            if (c.getName().equalsIgnoreCase(name)) { it.remove(); saveToFile(); System.out.println("Contact deleted."); return; }
        }
        System.out.println("Contact not found.");
    }

    public void deleteAll() {
        contacts.clear();
        saveToFile();
        System.out.println("All contacts deleted.");
    }

    public void toggleFavourite(String name) {
        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                c.setFavourite(!c.isFavourite());
                saveToFile();
                System.out.println("Favourite updated.");
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public void showFavourites() {
        boolean found = false;
        for (Contact c : contacts)
            if (c.isFavourite()) { c.display(); found = true; }
        if (!found) System.out.println("No favourite contacts.");
    }

    public void sortAZ() {
        contacts.sort(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER));
        System.out.println("Sorted A-Z.");
    }

    public void sortZA() {
        contacts.sort(Comparator.comparing(Contact::getName, String.CASE_INSENSITIVE_ORDER).reversed());
        System.out.println("Sorted Z-A.");
    }

    public void count() {
        System.out.println("Total contacts: " + contacts.size());
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Contact c : contacts)
                pw.println(c.getName() + "," + c.getNumber() + "," + c.isFavourite());
        } catch (Exception e) { System.out.println("Error saving file."); }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(",");
                if (data.length == 3)
                    contacts.add(new Contact(data[0], data[1], Boolean.parseBoolean(data[2])));
            }
        } catch (Exception e) { System.out.println("Error loading file."); }
    }
}