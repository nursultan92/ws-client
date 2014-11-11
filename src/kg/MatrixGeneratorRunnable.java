package kg;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class MatrixGeneratorRunnable implements Runnable {
    private int size;
    private int maxValue;

    public MatrixGeneratorRunnable(int size, int maxValue) {
        this.size = size;
        this.maxValue = maxValue;
    }

    public void run() {
        PrintWriter writerA = null, writerB = null;
        try {
            writerA = new PrintWriter("matrix_a.txt", "UTF-8");
            writerB = new PrintWriter("matrix_b.txt", "UTF-8");
            Random random = new Random(100);
            for (int index = 0; index < size; index++) {
                StringBuilder builderA = new StringBuilder();
                StringBuilder builderB = new StringBuilder();
                for (int jIndex = 0; jIndex < size; jIndex++) {
                    builderA.append(random.nextInt(maxValue) + " ");
                    builderB.append(random.nextInt(maxValue) + " ");
                }
                writerA.println(builderA.toString().trim());
                writerB.println(builderB.toString().trim());
            }
        } catch (FileNotFoundException e1) {
            System.err.println("File not found!");
        } catch (UnsupportedEncodingException e1) {
            System.err.println("Unsupported encoding format!");
        }
        writerA.close();
        writerB.close();
    }
}
