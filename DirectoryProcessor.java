import java.io.File;
import java.util.Set;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;

import java.util.stream.Stream;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


import java.util.Date;


public class DirectoryProcessor {

    public File directory = null;
    private String dateStamp = null;
    private static int POOL_SIZE = 5;
    private int processingListCounter = 0;
    public static Set<String> fileListProcessed = new HashSet<String>();

    public DirectoryProcessor(String directory, String dateStamp) {
        this.directory = new File(directory);
        this.dateStamp = dateStamp;
    }

    public void processFiles() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(POOL_SIZE);
        Set<String> fileList = new HashSet<String>();
        List<File> files = Arrays.asList(directory.listFiles());
        if (files != null) {
            files.stream()
            .filter(file -> validateFile(file) )
            .forEach(file -> {
                System.out.println("Processing File =>" + file.getName());
                FileProcessor fileprocess = new FileProcessor(file, dateStamp);
                executor.execute(fileprocess);
                fileList.add(file.getAbsolutePath());
                processingListCounter++;
                if(processingListCounter == POOL_SIZE){
                    awaitTermination(executor);
                    fileListProcessed.addAll(fileList);
                    fileList.clear();
                    processingListCounter = 0;
                }
            });
        }
        awaitTermination(executor);
        fileListProcessed.addAll(fileList);
    }

    private void awaitTermination(ThreadPoolExecutor executor) {
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isNotProcessed(File file) {
        return !fileListProcessed.contains(file.getAbsolutePath());
    }

    private boolean validateFile(File file) {
        return  validateIsFile(file) &&
        validateFilename(file) &&
        validateExt(file) &&
        validateModificationTime(file) &&
        isNotProcessed(file);
    }

    private boolean validateIsFile(File file) {
        return file.isFile();
    }

    private boolean validateExt(File file) {
        return file.getName().endsWith("log");
    }

    private boolean validateFilename(File file) {
        return file.getName().endsWith("log");
    }

    private boolean validateModificationTime(File file) {
       long todate = new Date().getTime();
       long fileModified = file.lastModified();
       long timeDiff = todate - fileModified;
       boolean inTimeLimit = timeDiff < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
       return inTimeLimit;
    }


}
