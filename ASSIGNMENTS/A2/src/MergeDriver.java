import java.util.Random;

public class MergeDriver {

    public static void main(String[] args) {

        String type = args[0];
        int[] arr;

        if (args.length > 2) {
            arr = new int[args.length-1];
            for (int i = 1; i < args.length; i++) {
                arr[i-1] = Integer.parseInt(args[i]);
            }
        }
        else {
            arr = new int[Integer.parseInt(args[1])];
            Random obj = new Random();
            for (int i = 0; i < arr.length; i++) {
                arr[i] = obj.nextInt(99);
            }
        }

        if (type.equals("MS")) {
            MergSort obj = new MergSort();
            obj.sort(arr);
        }
        else if (type.equals("SMS")) {
            StructuredMergSort obj = new StructuredMergSort();
            obj.sort(arr);
        }
    }
}
