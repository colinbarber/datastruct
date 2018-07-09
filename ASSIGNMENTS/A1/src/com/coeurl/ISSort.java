package com.coeurl;

public class ISSort {

    public static void main(String[] args) {
        long timeStart = System.nanoTime();
        int[] test = new int[args.length];

        for (int i = 0; i < args.length; i++) {
            test[i] = Integer.parseInt(args[i]);
        }

        System.out.print("Sorting: ");
        for (int item : test) {
            System.out.print(item+" ");
        }
        System.out.println();

        structure(test);
        int[] sorted = insertionSort(test);
        System.out.println("Completed in "+(System.nanoTime()-timeStart)+"ns");

        System.out.print("Sorted: ");
        for (int item : sorted) {
            System.out.print(item+" ");
        }
        System.out.println();

    }

    private static void structure(int[] str) {
        boolean runUp = false;
        boolean runDown = false;
        int saIndex = 0;
        int faIndex;
        int sdIndex = 0;
        int fdIndex;

        for (int i = 1; i < str.length; i++) {
            if (str[i] <= str[i-1]) {
                if (runDown) {
                    fdIndex = i;
                }
                else {
                    sdIndex = i - 1;
                    fdIndex = i;
                    runDown = true;
                }

                if (!(i < str.length-1 && str[i+1] <= str[i])) {
                    for (int j = sdIndex; j <= fdIndex; j++) {
                        System.out.print(str[j] + " ");
                    }
                    System.out.println("<");
                    runDown = false;
                }
            }

            if (str[i] >= str[i-1]) {
                if (runUp) {
                    faIndex = i;
                }
                else {
                    saIndex = i - 1;
                    faIndex = i;
                    runUp = true;
                }

                if (!(i < str.length-1 && str[i+1] >= str[i])) {
                    for (int j = saIndex; j <= faIndex; j++) {
                        System.out.print(str[j] + " ");
                    }
                    System.out.println(">");
                    runUp = false;
                }
            }
        }
    }

    private static int[] insertionSort(int[] sort) {
        int comparisons = 0;

        for (int i = 1; i < sort.length; i++) {
            if (sort[i] >= sort[i - 1]) {
                comparisons += 1;
                continue;
            }
            int temp = sort[i];
            for (int j = 0; j < i; j++) {
                sort[i-j] = sort[i-(1+j)];
                comparisons += 1;
                if (i-(2+j) >= 0 && temp < sort[i-(2+j)]) {
                    continue;
                }
                sort[(i-(1+j))] = temp;
                break;
            }
        }
        System.out.println("Comparisons: "+comparisons);
        return sort;
    }
}