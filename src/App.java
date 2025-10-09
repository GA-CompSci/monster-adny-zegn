import java.util.Scanner;

public class App {
    // CLASS VARIABLE
    private static Monster[] monsterParty;
    
    // PLAYER STATS
    private static int health = 100;
    private static int maxHealth = 100;
    private static int speed = 10;
    private static int shield = 50;
    private static int damage = 50;
    private static int heal = 50;

    // PLAYER STATE VARIABLES
    private static boolean isDefending;
    private static boolean gameOver;

    public static void main(String[] args) throws Exception {

        System.out.println("----- THE MONSTER PARTY BEGINS -----");

        Scanner input = new Scanner(System.in);
        System.out.print("How many monsters shall you slay? Speak: ");

        int num = input.nextInt(); // TODO: only allow positive ints
        monsterParty = new Monster[num];
        // invite all monsters to the party
        for (int i = 0; i < monsterParty.length; i++) {
            monsterParty[i] = new Monster(); // TODO: create some neat specials
        }

        // PICK YOUR BUILD
        System.out.println("----- PICK YOUR BUILD -----");
        System.out.println("1) Fighter");
        System.out.println("2) Tank");
        System.out.println("3) Cleric");
        System.out.println("4) Ninja");
        System.out.print("Choice: ");
        int build = input.nextInt();

        switch (build) {
            case 1: // fighters have less healing and shield
                shield -= (int)(Math.random() * (46)) + 5;
                heal -=  (int)(Math.random() * (46)) + 5;
                break;
            case 2: // tanks have less speed and damage
                speed -= (int)(Math.random() * (9)) + 1;
                damage -= (int)(Math.random() * (26)) + 5;
                break;
            case 3: // clerics have less damage and shield
                damage -= (int)(Math.random() * (26)) + 5;
                shield -= (int)(Math.random() * (46)) + 5;
                break;
            case 4: // ninjas have less heal and max health
                heal -= (int)(Math.random() * (46)) + 5;
                maxHealth -= (int)(Math.random() * (21)) + 5;
                health = maxHealth;
                break;
            default:
                System.out.print("\nChoice: ");
            }

        // DISPLAY THE STATUS OF THE MONSTER PARTY
        statusReport();

        // PICK A MONSTER
        Monster currentMonster = getNextMonster();

        // GAME LOOP
        while (gameOver == false) {
            isDefending = false;
            // TURN ORDER?

            // PLAYER OPTIONS
            System.out.println("\n----- OPTIONS -----");
            System.out.println("1) Attack");
            System.out.println("2) Defend");
            System.out.println("3) Heal");
            System.out.println("4) Pass");
            System.out.print("Choice: ");
            int choice = input.nextInt(); // TODO: Error handle on invalid input

            // THE CONSEQUENCES
            switch (choice) {
                case 1: // ATTACK
                    int dmg = (int)(Math.random() * (damage + 1));
                    if (dmg == damage) {
                        System.out.println("----- CRITICAL SUCCESS -----");
                        dmg = currentMonster.health();
                    } else if (dmg == 0) {
                        System.out.println("----- CRITICAL FAILURE -----");
                        System.out.println("You hit yourself for 10 hp!");
                        health -= 10;
                    } else System.out.print("You attacked for " + dmg + " damage. ");
                    currentMonster.takeDamage(dmg);
                    break;
                case 2: // SHIELD
                    isDefending = true;
                    System.out.println("Shield up!");
                    break;
                case 3: // HEAL
                    int h = (int)(Math.random() * (heal + 1));
                    if (health + h <= maxHealth) {
                        health += h;
                        System.out.println("You healed for " + h + " hp. Current health: " + health + " hp.");
                    } else {
                        h = maxHealth - health;
                        health += h;
                        System.out.println("You healed for " + h + " hp. Current health: " + health + " hp.");
                    }
                    break;
                case 4:
                    speed++;
                    System.out.println("Your speed has increased. Current speed: " + speed);
                    break;
                default:
                    continue;
            }

            // CHANGE MONSTER
            if (currentMonster.health() <= 0) {
                System.out.println("\nThe monster was slain.");
                currentMonster = getNextMonster();
                if (currentMonster == null) {
                    gameOver = true;
                } else {
                    statusReport();
                    System.out.println("\n----- BONUS TURN! -----");
                }
                continue;
            }

            // MONSTER'S TURN
            System.out.println("The monster's health: " + currentMonster.health() + " hp.");
            int speedCheck = (int)(Math.random() + (100));
            if (speedCheck <= speed) { // BONUS TURN
                System.out.println("You outsped the monster.");
                System.out.println("\n----- BONUS TURN! -----");
            } else {
                int incomingDamage = (int)(Math.random() * (currentMonster.damage() + 1));
                if (isDefending) {
                    incomingDamage -= shield;
                    if (incomingDamage <= 0) {
                    incomingDamage = 0;
                    System.out.print("CLANG! Your shield absorbed " + incomingDamage + " damage.");
                    } else System.out.print("CLANG! Your shield absorbed " + shield + " damage." + "\nYou took " + incomingDamage + " damage.");
                } else System.out.print("You have taken " + incomingDamage + " damage.");
                health -= incomingDamage;
                System.out.println(" Current health: " + health + " hp.");
            }

            // ARE WE DEAD???
            if (health <= 0) {
                System.out.println("\n\n----- YOU HAVE DIED. -----");
                gameOver = true;
            }
        }

        // GAME END
        if (health > 0 ) {
            System.out.println("Congratulations, hero! You slew " + monsterParty.length + " monsters! Try again?");
        }
    }

    /**
     * Returns how many monsters have MORE than the given health.
     * @param health The health threshold to check.
     * @return The number of monsters with hp above the given.
     */
    public static int monsterCount(int health) {
        int count = 0;
        for (Monster monster : monsterParty) {
            if (monster.health() > health) count++;
        }
        return count;
    }

    /**
     * Returns the next monster of the monster party.
     * @return The next monster that is still alive.
     */
    public static Monster getNextMonster() {
        for (int i = 0; i < monsterParty.length; i++) {
            if (monsterParty[i].health() > 0) return monsterParty[i];
        }
        return null;
    }

    /**
     * Returns the percent completion of the slaughter.
     * @return Proportion of monsters already defeated.
     */
    public static double percentComplete() {
        double fallen = monsterParty.length - monsterCount(0);
        return (fallen / monsterParty.length) * 100;
    }

    /**
     * Creates a report of all monsters, displaying their attributes.
     */
    public static void statusReport() {
        System.out.println("\n----- THE OFFICIAL MONSTER REPORT -----");
        for (int i = 0; i < monsterParty.length; i++) {
            System.out.println("Monster " + (i + 1) + ":");
            if (monsterParty[i].health() <= 0) {
                System.out.println("Dead.");
                continue;
            }

            System.out.print(monsterParty[i].health() + " hp, ");
            System.out.print(monsterParty[i].damage() + " atk, ");
            System.out.print(monsterParty[i].speed() + " spd. ");

            if (monsterParty[i].special().equals("")) {
                System.out.print("Normal monster.");
            } else System.out.print("Elite ability: " + monsterParty[i].special());

            System.out.println();
        }

        System.out.println("\n----- THE OFFICIAL PLAYER REPORT -----");
        System.out.println("HEALTH: " + health);
        System.out.println("ATTACK: " + damage);
        System.out.println("SPEED: " + speed);
        System.out.println("SHIELD: " + shield);
        System.out.println("HEAL: " + heal);

        // HEALTH / PROGRESS BARS
        int hashCount = Math.max(0, (health * 20 / maxHealth));
        int dashCount = Math.max(0, (20 - hashCount));
        String hashes = "#".repeat(hashCount);
        String dashes = "-".repeat(dashCount);
        System.out.println("PLAYER HEALTH: [" + hashes + dashes + "] (" + health + "/" + maxHealth + ")");
        hashes = "#".repeat(20 - (int)percentComplete() / 5);
        dashes = "-".repeat((int)percentComplete() / 5);
        System.out.println("MONSTERS LEFT: [" + hashes + dashes + "] (" + monsterCount(0) + "/" + monsterParty.length + ")");
    }

    /**
     * Returns the monster with the lowest health, but still living from the monster party.
     * Returns null if the slaughter is completed.
     * @return
     */
    public static Monster getWeakestMonster() {
        Monster weakest = getNextMonster();
        if (weakest == null) return null;
        for (Monster monster : monsterParty) {
            if (monster.health() < weakest.health() && monster.health() > 0) {
                weakest = monster;
            }
        }
        return weakest;
    }

}
