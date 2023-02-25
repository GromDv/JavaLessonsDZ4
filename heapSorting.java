package Java_DZ4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.*;

public class heapSorting {

    static Logger logger = Logger.getLogger(heapSorting.class.getName());

    public static void main(String[] args) throws SecurityException, IOException {

        FileHandler fh = new FileHandler("log.txt");
        logger.addHandler(fh);
        SimpleFormatter sFormat = new SimpleFormatter();
        fh.setFormatter(sFormat);
        logger.info("Начало!");

        String[] st = readData("input.txt").split(",");
        int[] arr = new int[st.length];
        arr = Arrays.stream(st).mapToInt(s -> Integer.valueOf(s.trim())).toArray();

        logger.info(Arrays.toString(arr));
        heapSort(arr);
        logger.info(Arrays.toString(arr));

        writeData("output.txt", Arrays.toString(arr));

    }

    public static void heapSort(int[] array) {
        int temp;
        int size = array.length - 1;
        for (int i = (array.length / 2); i >= 0; i--) 
            heapify(array, i, size);
        
        for (int i = size; i >= 0; i--) {
            temp = array[0];
            array[0] = array[size];
            array[size] = temp;
            size--;
            heapify(array, 0, size);
        }
    }

    public static void heapify(int[] array, int i, int heapSize) {
        int a = 2 * i;
        int b = 2 * i + 1;
        int bigestEl;
        if (a <= heapSize && array[a] > array[i])
            bigestEl = a;
        else
            bigestEl = i;

        if (b <= heapSize && array[b] > array[bigestEl])
            bigestEl = b;

        if (bigestEl != i) {
            int temp = array[i];
            array[i] = array[bigestEl];
            array[bigestEl] = temp;
            heapify(array, bigestEl, heapSize);
        }
    }

    static String readData(String file) {
        String line = "";
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(file));
            line = bufReader.readLine();
            bufReader.close();
            logger.info("Входные данные прочитаны!");
        } catch (IOException ex) {
            System.out.println("Error reading!");
            System.out.println(ex.getMessage());
        }
        return line;
    }

    static void writeData(String file, String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(data);
            logger.info("Выходные данные записаны!");
        } catch (IOException ex) {
            System.out.println("Error writing!");
            System.out.println(ex.getMessage());
        }
    }
}
