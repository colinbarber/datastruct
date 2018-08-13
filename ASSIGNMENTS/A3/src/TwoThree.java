import java.io.File;
import java.util.Scanner;


public class TwoThree {

    public static void main(String[] args) throws Exception {
        // takes a list of commands to add and find nodes in a tree. Accepts a .txt file and step number for preorder traversal.
        Tree twoThree = new Tree();
        int add = 0;
        int find = 0;
        int remove = 0;
        String traversal = "";

        // first separate the traversal step.
        int step = Integer.parseInt(args[1]);
        int count = 0;

        // turns the txt file into a list of commands. SOURCE: https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);

            // interpret the commands
            while (sc.hasNextLine()) {
                count += 1;
                String nextLine = sc.nextLine();
                String command = nextLine.substring(0,1);

                // add command.
                if (command.equals("a")) {
                    add += 1;
                    twoThree.add(Integer.parseInt(nextLine.substring(1,4)));
                }
                // find command.
                else if (command.equals("f")) {
                    find += 1;
                    twoThree.find(Integer.parseInt(nextLine.substring(1,3)));
                }
                // remove command.
                else if (command.equals("r")) {
                    remove += 1;
                }

                // prints preorder traversal.
                if (count == step) {
                    traversal = "Pre-order traversal after step "+Integer.toString(step)+": "+twoThree.traversal(twoThree.root);
                }
            }

            // prints comparisons made, 3-nodes created, add operations, find operations, remove operations, and pre-order traversal.
            System.out.println(Integer.toString(twoThree.comparisons)+" Comparisons Made");
            System.out.println(Integer.toString(twoThree.threeNodes)+" Three Nodes Created");
            System.out.println(Integer.toString(add)+" add operations");
            System.out.println(Integer.toString(find)+" find operations");
            System.out.println(Integer.toString(remove)+" remove operations");
            System.out.println(traversal);
    }
}