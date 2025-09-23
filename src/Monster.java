public class Monster {
    // INSTANCE VARIABLES - PROPERTIES - ATTRIBUTES
    private int health;
    private double damage;
    private int speed;
    private String special;

    // CONSTRUCTOR
    public Monster() {
        // RANDOM ATTRIBUTE GENERATION
        health = (int)(Math.random() * 81) + 20;
        // damage: random 10-50
        damage = (Math.random() * 41) + 10;
        // speed: random 1-10
        speed = (int)(Math.random() * 9) + 1;
        // no special by default
        special = "";
    }
    // OVERLOADED CONSTRUCTOR
    public Monster(String special) {
        this();
        this.special = special;
    }

    // ACCESSOR METHODS
    public int health() { return this.health; }
    public double damage() { return Math.round(this.damage * 10.0) / 10.0; }
    public int speed() { return this.speed; }
    public String special() { return this.special; }
    
}
