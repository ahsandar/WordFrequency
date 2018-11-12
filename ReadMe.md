The process starts with

`DirectoryProcessor.java` It reads the files for last 24hours based on the naming convetion and has a Threadpool based on its size it starts processing files. Also maintains a list of files that are processed.

`FileProcessor.java` Each File process reads a specific number of lines and then forwards them to MultiLinePocessor.java to run string frequency concurrently

`MultiLineProcessor.java` This class takes number of lines and passes each per thread to WordFrequncy.java to process them concurrently

`WordFrequency.java` This class takes each line parses it based on requirements and add the count to WordCount.java

`WordCount.java` This class holds the count of each word per day, There is a concurrent hash map for each day that holds word and count concurent hashmap.

`Input` format

```
11111111, DgTia7FW6Qt4otfZ
11111111, 9GYeIRs75JstptQf
11111111, DgTia7FW6Qt4otfZ
11111111, 9GYeIRs75JstptQf
11111111, 6ogzqroLAENUcNMn
11111111, HXLosBDYoUnTGErV
11111111, ZDIsw0r3gfzoJ4ET
11111111, jBSM1FbaPKHVm1pY
```