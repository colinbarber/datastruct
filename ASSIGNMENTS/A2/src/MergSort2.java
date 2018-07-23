public class MergSort2{

    public static void sort(int[] arr) {

        int[] bfr = new int[arr.length];
        JumpList runs = new JumpList(arr.length);

        for (int i = 1; i < arr.length; i *= 2) {
            for (int j = 0; j < arr.length/i; j++) {
                if ((i*j)+(i-1) <= arr.length-1) {
                    ARun run = new ARun((i * j), (i * j) + (i - 1));
                    runs.enqueue(run);
                    }
                else {
                    ARun run = new ARun((i * j), arr.length-1);
                    runs.enqueue(run);
                }
                if (j%2 != 0 || j == arr.length/i) {
                    if (i == 1) {
                        merge(arr, bfr, runs.dequeue(), runs.dequeue());
                    } else {
                        merge(bfr, bfr, runs.dequeue(), runs.dequeue());
                    }
                }
            }
        }
    }
    public static void merge(int[] src, int[] dst, ARun run1, ARun run2) {

    }
}
