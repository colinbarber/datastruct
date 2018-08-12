import java.io.BufferedReader;
import java.io.FileReader;


public class TwoThree {

    public static void main(String[] args) {
        // takes a list of commands to add and find nodes in a tree. Accepts a .txt file.
        String[] commands = extract(args);
        Tree twoThree = new Tree();

        // interprets the commands.
        for (line : commands) {
            if (line == add val) {
                Node i = new Node(val)
                twoThree.add(i);
            }
            else if (line == find) {
                twoThree.find(line);
            }
        }

    }

    // turns the txt file into an array of commands.
    void extract(String[] i) {
        FileReader input = new FileReader("args");
        BufferedReader bufRead = new BufferedReader(input);
        String myLine = null;

        while ( (myLine = bufRead.readLine()) != null)
        {
            String[] array1 = myLine.split(":");
            // check to make sure you have valid data
            String[] array2 = array1[1].split(" ");
            for (int i = 0; i < array2.length; i++)
                function(array1[0], array2[i]);
        }
    }
}