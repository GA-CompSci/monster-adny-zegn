import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        private static Monster[] monsterParty;

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
     * @return 
     */
    public static Monster getNextMonster() {
        for (int i = 0; i < monsterParty.length; i++) {

        }
    }
}
