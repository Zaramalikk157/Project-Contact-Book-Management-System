public abstract class Person {
    protected String name;
    protected String number;

    public Person(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public abstract void display();
}