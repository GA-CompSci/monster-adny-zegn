public class Weapon {
    // instance variable
    private int damage;
    private String name;

    // constructor
    public Weapon(int damage, String name) {
        this.name = name;
        this.damage = damage;
    }

    // accessors
    public int damage() {return this.damage;}
    public String name() {return this.name;}
}
