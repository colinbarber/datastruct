package com.coeurl;

public class ISort {

    public static void main(String[] args) {
        long timeStart = System.nanoTime();
        int[] test = new int[args.length];

        for (int i = 0; i < args.length; i++) {
            test[i] = Integer.parseInt(args[i]);
        }

        for (int item : test) {
            System.out.print(item+" ");
        }
        System.out.println();

        int[] sorted = insertionSort(test);
        System.out.println("Completed in "+(System.nanoTime()-timeStart)+"ns");
        System.out.print("Sorted: ");
        for (int item : sorted) {
            System.out.print(item+" ");
        }
        System.out.println();
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