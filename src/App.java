import java.util.Scanner;

public class App {
    private static Monster[] monsterParty;
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

        // HOW MANY MONSTERS HAVE >50 HEALTH???
        System.out.println(monsterCount(50, monsterParty) + " monsters have more than 50 hp! Egad!");

        // WHAT IS THE PERCENT COMPLETION OF THE SLAUGHTER???
        System.out.println("Current progress: " + percentComplete() + "%");

        // DISPLAY THE STATUS OF THE MONSTERPARTY
        reportMonsters();
    }

    /**
     * Returns how many monsters have MORE than the given health.
     * @param health The health threshold to check.
     * @param monsterFamily The monster array to check.
     * @return The number of monsters with hp above the given.
     */
    public static int monsterCount(int health, Monster[] monsterFamily) {
        int count = 0;
        for (Monster monster : monsterFamily) {
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
        double fallen = monsterParty.length - monsterCount(0, monsterParty);
        return (fallen / monsterParty.length) * 100;
    }

    /**
     * Creates a report of all monsters, displaying their attributes.
     */
    public static void reportMonsters() {
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
    }
}
