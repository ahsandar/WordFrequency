import java.util.Arrays;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class FileProcessor implements Runnable {

    private File file = null;
    private String dateStamp = null;
    private static int POOL_SIZE = 5;
    private MultiLineProcessor multiplexor = new MultiLineProcessor();

    public FileProcessor(File file, String dateStamp) {
        this.file = file ;
        this.dateStamp = dateStamp;
    }

    public void run() {
        try {

            BufferedReader b = new BufferedReader(new FileReader(file));

            int index = 0;
            String[] readLines = new String[POOL_SIZE];

            System.out.println("Reading file using Buffered Reader");

            while ((readLines[index] = b.readLine()) != null) {
                if ((index == (readLines.length - 1)) || (readLines[index] == null)) {
                    multiplexor.processWordFrequency(readLines, dateStamp);
                    index = 0;
                    Arrays.fill(readLines, null);
                } else {
                    index++;
                }
            }
            multiplexor.processWordFrequency(readLines, dateStamp);
            b.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}