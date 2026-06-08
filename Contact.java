public class Contact extends Person {
    private boolean favourite;

    public Contact(String name, String number, boolean favourite) {
        super(name, number);
        this.favourite = favourite;
    }

    public String getName() { return name; }
    public String getNumber() { return number; }
    public boolean isFavourite() { return favourite; }

    public void setName(String name) { this.name = name; }
    public void setNumber(String number) { this.number = number; }
    public void setFavourite(boolean favourite) { this.favourite = favourite; }

    @Override
    public void display() {
        if (favourite)
            System.out.println("★ " + name + " - " + number);
        else
            System.out.println(name + " - " + number);
    }
}