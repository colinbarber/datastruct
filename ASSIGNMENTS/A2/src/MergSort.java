public class MergSort{
    static int comparisons = 0;

    public static void sort(int[] arr) {
        long timeStart = System.nanoTime();

        int[] bfr = new int[arr.length];
        JumpList runs = new JumpList(arr.length);

        System.out.print("sorting: ");
        for (int item : arr) {
            System.out.print(item+" ");
        }
        System.out.println();

        for (int i = 1; i < arr.length; i *= 2) {

            for (int j = 0; j < arr.length/i; j++) {
                if ((i*j)+(i-1) <= arr.length-1) {
                    ARun run = new ARun((i * j), i);
                    runs.enqueue(run);
                }
                else {
                    ARun run = new ARun((i * j), arr.length-1);
                    runs.enqueue(run);
                }
            }
            while (!runs.isEmpty()) {
                if (i == 1) {
                    merge(arr, bfr, runs.dequeue(), runs.dequeue());
                } else {
                    merge(bfr, bfr, runs.dequeue(), runs.dequeue());
                }
            }
            System.out.print("bfr: ");
            for (int item : bfr) {
                System.out.print(item+" ");
            }
            System.out.println();
        }

        System.out.println("comparisons: "+comparisons);
        System.out.print("result: ");
        for (int item : bfr) {
            System.out.print(item+" ");
        }
        System.out.println();
        System.out.println("Completed in "+(System.nanoTime()-timeStart)+"ns");
    }

    public static void merge(int[] src, int[] dst, ARun run1, ARun run2) {
        int loc1 = run1.getStart();
        int loc2 = run2.getStart();

        if (run2.getLength() != 0) {
            for (int i = run1.getStart(); i < run1.getLength()+run2.getLength(); i++) {

                if (loc1 < run1.getStart()+run1.getLength() && loc2 < run2.getStart()+run2.getLength()) {
                    if (src[loc1] <= src[loc2]) {
                        comparisons += 1;
                        dst[i] = src[loc1];
                        System.out.println("set bfr["+i+"] = "+src[loc1]);
                        loc1++;
                    } else if (src[loc2] <= src[loc1]) {
                        comparisons += 1;
                        dst[i] = src[loc2];
                        System.out.println("set bfr["+i+"] = "+src[loc2]);
                        loc2++;
                    }
                }
                else if (loc1 < run1.getStart()+run1.getLength()) {
                    comparisons += 1;
                    dst[i] = src[loc1];
                    System.out.println("set bfr["+i+"] = "+src[loc1]);
                    loc1++;
                }
                else if (loc2 < run2.getStart()+run2.getLength()) {
                    comparisons += 1;
                    dst[i] = src[loc2];
                    System.out.println("set bfr["+i+"] = "+src[loc2]);
                    loc2++;
                }
            }
        }
        else {
            for (int i = run1.getStart(); i < run1.getStart()+run1.getLength(); i++) {
                dst[i] = src[loc1];
                System.out.println("set bfr["+i+"] = "+src[loc1]);
                loc1++;
            }
        }
    }
}
