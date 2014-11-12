package kg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Utilities {

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c <= '/' || c >= ':') {
                return false;
            }
        }
        return true;
    }

    public static int[] stringArrayToIntArray(String[] strings) {
        int[] a = new int[strings.length];
        for (int index = 0; index < strings.length; index++) {
            a[index] = Integer.parseInt(strings[index]);
        }
        return a;
    }

    public static int[][] parallelMult(int[][] A, int[][] B, int threadNumber) {
        int[][] C = new int[A.length][A.length];
        ExecutorService executor = Executors.newFixedThreadPool(threadNumber);
        List<Future<int[][]>> list = new ArrayList<Future<int[][]>>();

        int part = A.length / threadNumber;
        if (part < 1) {
            part = 1;
        }
        for (int i = 0; i < A.length; i += part) {
            System.err.println(i);
            Callable<int[][]> worker = new LineMultiplier(A, B, i, i + part,"localhost");
            Future<int[][]> submit = executor.submit(worker);
            list.add(submit);
        }

        // now retrieve the result
        int start = 0;
        int CF[][];
        for (Future<int[][]> future : list) {
            try {
                CF = future.get();
                for (int i = start; i < start + part; i += 1) {
                    C[i] = CF[i];
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            start += part;
        }
        executor.shutdown();

        return C;
    }
}
