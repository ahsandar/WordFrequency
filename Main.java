import java.util.Scanner;


public class Main {

    private static String DIR = "/c/usr/code/WordFrequency/log";

    public static void main(String[] args) {

        for(int i = 0; i <2; i++){
            DirectoryProcessor fileProcessor = new DirectoryProcessor(DIR, Utility.dateStampYesterday());
            fileProcessor.processFiles();
        }
        userLookup();
    }

    private static void userLookup() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        System.out.println("Enter Word: ");
        while (!(input = scanner.next()).equals("STOP")) {
            String string = "Count for %s: %d";
            System.out.println(String.format(string, input, WordCount.getCount(Utility.dateStampYesterday(), input.toLowerCase())));
            System.out.println("Enter Word: ");
        }
        System.out.println("Adios !!!!");
        scanner.close();
    }

}
