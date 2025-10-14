import java.util.ArrayList;
import java.util.Scanner;

public class Nums {
    public static void main(String[] args) {
        // ArrayLists have extra functionalities as opposed to arrays
        ArrayList<String> nameList = new ArrayList<>();

        Scanner s = new Scanner(System.in);
        System.out.print("Enter name: ");
        String input = s.nextLine();
        while(!input.equals("")) {
            nameList.add(input);
            System.out.print("Enter next name: ");
            input = s.nextLine();
        }

        while(!input.equalsIgnoreCase("quit") || nameList.size() == 0) {
            int r = (int)(Math.random() * nameList.size());
            System.out.println("Randomly selected... " + nameList.get(r));
            nameList.remove(r);
            System.out.println("Enter to draw another name, type QUIT to exit");
            input = s.nextLine();
        }
    }
}
