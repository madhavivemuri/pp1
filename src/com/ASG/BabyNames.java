
package com.ASG;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.*;

public class BabyNames {

    public static void() throws IOException {
        String expected_value = " ";
        //   FileResource fr=new FileResource("C:\\Users\\madhu\\OneDrive\\Desktop\\yob2012short.csv");

        Path path = Paths.get("C:\\Users\\madhu\\OneDrive\\Desktop\\yob2012short.csv");

        BufferedReader reader = Paths.newBufferedReader(path);
        String line = reader.readLine();
    }


//    Modify the method totalBirths (shown in the video for this project) to also print the number
//    of unique girls names , the number of unique boys names and the total names in the file.
    public void totalBirths(Path fr) {
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        CSVParser parser = Path.getCSVParser(false);

        for (CSVRecord record : parser) {
            int numBorn = Integer.parseInt(record.get(2));
            String gender = record.get(1);
            totalBirths += numBorn;
            if (gender.equals("M")) {
                totalBoys += numBorn;
            } else {
                totalGirls += numBorn;
            }
        }

        System.out.println("Total: " + totalBirths);
        System.out.println("Boys: " + totalBoys);
        System.out.println("Girls: " + totalGirls);
    }
    }


//    Write the method named getRank that has three parameters: an integer named year , a
//    string named name , and a string named gender (F for female and M for male). This
//    method returns the rank of the name in the file for the given gender, where rank 1 is the
//    name with the largest number of births. If the name is not in the file, then 1 is returned.
//    For example, in the file "yob2012short.csv", given the name Mason, the year 2012 and the
//    gender ‘M’, the number returned is 2, as Mason is the boys name with the second highest
//    number of births. Given the name Mason, the year 2012 and the gender ‘F’, the number
//    returned is 1 as Mason does not appear with an F in that file.


    Public long getRank(int year, String name, String gender) {
        long rank = -1;
        FileResource fr = new FileResource("C:\\Users\\madhu\\OneDrive\\Desktop\\yob2012short.csv");        CSVParser parser = fr.getCSVParser(false);

        for(CSVRecord record : parser) {
            String currName = record.get(0);
            String currGender = record.get(1);

            if(currGender.equals(gender) && currName.equals(name)) {
                rank = record.getRecordNumber();
            }
        }
        return rank;
}


//Write the method named getName that has three parameters:an integer named year,an integer named
// rank,and a string named gender (F for female and M for male). This method returns the name of the
// person in the file at this rank, for the given gender, where rank 1 is the name with the largest
// number of births. If the rank does not exist in the file,then “NO NAME” is returned.

    Public String getName(int year, int rank, String gender){

    String name="";
    FileResource fr=new FileResource("C:\\Users\\madhu\\OneDrive\\Desktop\\yob2012short.csv");
    CSVParser parser=fr.getCSVParser(false);

    for(CSVRecord record:parser){
        long currRank=record.getRecordNumber();
        String currGender=record.get(1);
        String currName=record.get(0);

        if(currRank==rank&&currGender.equals(gender)){
        name=currName;
        }
    }
    if(name != null) {
        return name;
    }
    else {
        return "NO NAME";
    }
}



//What would your name be if you were born in a different year? Write the void method named
//whatIsNameInYear that has four parameters: a string name , an integer named year representing
//the year that name was born, an integer named newYear and a string Java Programming:
//Solving Problems with Software named gender (F for female and M for male). This method determines
//what name would have been named if they were born in a different year, based on the same popularity.
//That is, you should determine the rank of name in the year they were born, and then print the name
//born in newYear that is at the same rank and same gender. For example, using the files
//"yob2012short.csv" and "yob2014short.csv", notice that in 2012 Isabella is the third most popular
//girl's name. If Isabella was born in 2014 instead, she would have been named Sophia, the third most
//popular girl's name that year. The output might look like this:
//Isabella born in 2012 would be Sophia if she was born in 2014.

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        FileResource fr = new FileResource("C:\\Users\\madhu\\OneDrive\\Desktop\\yob2012short.csv");
        FileResource newFr = new FileResource("C:\\Users\\madhu\\OneDrive\\Desktop\\yob2012short.csv");
        CSVParser parserOld = fr.getCSVParser(false);
        CSVParser parserNew = newFr.getCSVParser(false);
        String newName = "";
        long popularity = 0;

        for(CSVRecord record : parserOld) {
            String currName = record.get(0);
            String currGender = record.get(1);

            if(currName.equals(name) && currGender.equals(gender)) {
                popularity = record.getRecordNumber();
            }
        }

        for(CSVRecord record : parserNew) {
            String currGender = record.get(1);
            long currPopularity = record.getRecordNumber();

            if(currGender.equals(gender) && popularity == currPopularity) {
                newName = record.get(0);
            }
        }

        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }



//Write the method yearOfHighestRank that has two parameters: a string name , and a string named gender
//(F for female and M for male).This method selects a range of files to process and returns an integer,
//the year with the highest rank for the name and gender. If the name and gender are not in any of the
//selected files, it should return 1. For example,calling yearOfHighestRank with name Mason and gender
//‘M’ and selecting the three test files above results in returning the year 2012. That is because
//Mason was ranked the 2nd most popular name in 2012, ranked 4th in 2013 and ranked 3rd in 2014. His
//highest ranking was in 2012.


    public int yearOfHighestRank(String name, String gender) {
        long highestRank = 0;
        int yearOfHighestRank = -1;
        String fileName = "";
        DirectoryResource dr = new DirectoryResource();

        // Iterate through all files
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);

            // Iterate through all records in file
            for(CSVRecord record : parser) {
                String currName = record.get(0);
                String currGender = record.get(1);

                if(currName.equals(name) && currGender.equals(gender)) {
                    long currRank = record.getRecordNumber();

                    if(highestRank == 0) {
                        highestRank = currRank;
                        fileName = f.getName();
                    }
                    else {
                        if(highestRank > currRank) {
                            highestRank = currRank;
                            fileName = f.getName();
                        }
                    }
                }
            }
        }
        // Remove all non-numeric characters from the filename
//        fileName = fileName.replaceAll("[^\\d]", "");

        // Convert String fileName to Integer
        yearOfHighestRank = Integer.parseInt(fileName);

        return yearOfHighestRank;
    }

//Write the method getAverageRank that has two parameters: a string name , and a string named gender
//(F for female and M for male). This method selects a range of files to process and returns a double
//representing the average rank of the name and gender over the selected files. It should return 1.0
//if the name is not ranked in any of the selected files.
//For example calling getAverageRank with name Mason and gender ‘M’ and selecting the three test files
//above results in returning 3.0, as he is rank 2 in the year 2012, rank 4 in 2013 and rank 3 in 2014.
//As another example, calling getAverageRank with name Jacob and gender ‘M’ and selecting the three
//test files above results in returning 2.66.

        Public double getAverageRank(String name,String gender) {
        // Initialize a DirectoryResource
        DirectoryResource dr = new DirectoryResource();
        // Define rankTotal, howMany
        double rankTotal = 0.0;
        int howMany = 0;
        // For every file the directory add name rank to agvRank
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            for(CSVRecord record : parser) {
                String currName = record.get(0);
                String currGender = record.get(1);
                if(currName.equals(name) && currGender.equals(gender)){
                    long currRank = record.getRecordNumber();
                    rankTotal += (double)currRank;
                    howMany += 1;
                }
            }
        }
        // Define avgRank = rankTotal / howMany
        double avgRank = rankTotal / (double)howMany;
        return avgRank;
    }



//Write the method getTotalBirthsRankedHigher that has three parameters: an integer named year , a
//string named name , and a string named gender (F for female and M for male). This method returns
//an integer, the total number of births of those names with the same gender and same year who are
//ranked higher than name . For example, if getTotalBirthsRankedHigher accesses the "yob2012short.csv"
//file with name set to “Ethan”, gender set to “M”, and year set to 2012, then this method should
//return 15, since Jacob has 8 births and Mason has 7 births, and those are the only two ranked higher
//than Ethan.


    Public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int numBorn = 0;
        long rank = getRank(year, name, gender);
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord record : parser) {
            int currBorn = Integer.parseInt(record.get(2));
            String currGender = record.get(1);
            long currRank = record.getRecordNumber();
            if(gender.equals(currGender) && rank > currRank) {
                numBorn += currBorn;
            }
        }
        return numBorn;
    }

//Testing the methods

    Public void testTotalBirth() {
        FileResource fr = new FileResource("C:\\\\Users\\\\madhu\\\\OneDrive\\\\Desktop\\\\yob2012short.csv\");
        totalBirths(fr);

    }

    Public void main() {
        BabyNames names = new BabyNames();
        names.testTotalBirth();
    }
}
}
