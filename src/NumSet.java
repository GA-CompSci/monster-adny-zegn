import java.util.ArrayList;

public class NumSet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        ROUND 1 tests
        */
        
        // Create a random int array of a given length, low and high end of range
        int[] randArray = randArray(15, 0, 100);
        
        // Create a random Integer ArrayList of given length, low and high range
        ArrayList<Integer> randArrL = randArrL(8, 5, 50);
        
        // How many similar elements are in a given array and ArrayList
        System.out.print("There are this many similar elements: ");
        System.out.println(compareNums(randArray, randArrL));
        
        // printPretty takes an int array and prints it out nicely
        printPretty(randArray);
        // printPretty takes an Integer ArrayList and prints it out nicely
        printPretty(randArrL);
        
        /*
        ROUND 2 tests
        */
        
        // shuffle randomizes an int array (then calls printPretty)
        shuffle(randArray);
        
        // shuffle randomizes an Integer ArrayList (then calls printPretty)
        shuffle(randArrL);
        
        // divide all numbers by two
        divByTwo(randArray);
        divByTwo(randArrL);
        
        //sumArray
        System.out.println(sumArray(randArray));
        System.out.println(sumArray(randArrL));
        
    }
    /*
    ROUND 1 code
    */

    public static int[] randArray(int length, int min, int max) {
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = (int)(Math.random() * (max - min + 1)) + min;
        }
        return result;
    }

    public static ArrayList<Integer> randArrL(int length, int min, int max) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < length; i++) {
            result.add((int)(Math.random() * (max - min + 1)) + min);
        }
        return result;
    }
    
    public static int compareNums(int[] arr, ArrayList<Integer> arrL) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arrL.indexOf(arr[i]) != -1) {
                count++;
            }
        }
        return count;
    }
    
    public static void printPretty(int[] arr) {
        System.out.println("Printing array of length " + arr.length + ":");
        for (int num : arr) {
            System.out.println(num);
        }
    }
    
    public static void printPretty(ArrayList<Integer> arrL) {
        System.out.println("Printing ArrayList of length " + arrL.size() + ":");
        for (int num : arrL) {
            System.out.println(num);
        }
    }

    /*
    ROUND 2 code
    */
    
    public static void shuffle(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // Pick new location
            int newIndex = (int)(Math.random() * arr.length);
            // Three-part-swap
            int temp = arr[newIndex];
            arr[newIndex] = arr[i];
            arr[i] = temp;
        }
        printPretty(arr);
    }
    
    public static void shuffle(ArrayList<Integer> arrL) {
        for (int i = 0; i < arrL.size(); i++) {
            int newIndex = (int)(Math.random() * arrL.size());
            // Three-part-swap
            int temp = arrL.get(newIndex);
            arrL.set(newIndex, arrL.get(i));
            arrL.set(i, temp);
        }
        printPretty(arrL);
    }
    
    /*
     * For the sake of simplicity, divByTwo uses int math and runs printPretty at the end.
     * There will be inaccuracies when odd numbers are in the array or ArrayList.
     */
    public static void divByTwo(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i] / 2;
        }
        printPretty(result);
    }

    public static void divByTwo(ArrayList<Integer> arrL) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < arrL.size(); i++) {
            int half = arrL.get(i) / 2;
            result.add(half);
        }
        printPretty(result);
    }
    
    public static int sumArray(int[] arr) {
        int total = 0;
        for (int num : arr) {
            total += num;
        }
        return total;
    }

    public static int sumArray(ArrayList<Integer> arrL) {
        int total = 0;
        for (int num : arrL) {
            total += num;
        }
        return total;
    }

}