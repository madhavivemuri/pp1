package com.asgm.java;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class BabyBirths {

    public static void main(String[] args){

        BabyBirths babyTestBirths = new BabyBirths();
        babyTestBirths.testtotalBirths();
        babyTestBirths.testgetRank();
        babyTestBirths.testgetName();
        babyTestBirths.testwhatisNameInYear();
        babyTestBirths.testyearOfHighestRank();
        babyTestBirths.testgetAverageRank();
        babyTestBirths.testgetTotalBirthsRankedHigher();
    }

/* Modify the method totalBirths (shown in the video for this project) to also print the number
 of unique girls names , the number of unique boys names and the total names in the file.*/

    public void totalBirths(CSVParser parser) {
        int totalboys = 0;
        int totalgirls = 0;
        int total = 0;
        int totaluniqueboys = 0;
        int totaluniquegirls = 0;
        int totalunique = 0;
        for (CSVRecord record : parser) {
            if (record.get(1).equals("F")) {
                total = total + 1;
                totalgirls = totalgirls + Integer.parseInt(record.get(2));

                totaluniquegirls = totaluniquegirls + 1;
                totalunique = totalunique + 1;
            } else {
                total = total + 1;
                totalboys = totalboys + Integer.parseInt(record.get(2));
                totaluniqueboys = totaluniqueboys + 1;
                totalunique = totalunique + 1;
            }

        }
        System.out.println("Total number of boys: " + totalboys);
        System.out.println("Total number of girls: " + totalgirls);
        System.out.println("Total numbers: " + total);
        System.out.println("Total unique number boys: " + totaluniqueboys);
        System.out.println("Total unique number girls: " + totaluniquegirls);
        System.out.println("Total unique numbers: " + totalunique);
    }

    public void testtotalBirths() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        totalBirths(parser);
    }



/* Write the method named getRank that has three parameters: an integer named year ,a string
   named name , and a string named gender (F for female and M for male). This method returns
   the rank of the name in the file for the given gender, where rank 1 is the name with the
   largest number of births. If the name is not in the file, then 1 is returned. For example,
   in the file "yob2012short.csv", given the name Mason, the year 2012 and the gender ‘M’,
   the number returned is 2, as Mason is the boys name with the second highest number of births.
   Given the name Mason, the year 2012 and the gender ‘F’, the number returned is 1 as Mason
   does not appear with an F in that file.*/


    public int getRank(int year, String name, String gender) {
        String na = "";
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        int number = 0;
        for (CSVRecord record : parser) {
            if (record.get(1).equals(gender)) {
                rank = rank + 1;
                if (record.get(0).equals(name)) {
                    number = 1;
                    break;
                }
            }
        }
        if (number == 1) return rank;
        else return 1;
    }

    public void testgetRank() {
        int rank = getRank(2012, "Mason", "F");
        System.out.println("rank is "+ rank);
    }



/* Write the method named getName that has three parameters: an integer named year,an integer named
   rank and a string named gender (F for female and M for male).This method returns the name of the
   person in the file at this rank, for the given gender, where rank 1 is the name with the largest
   number of births. If the rank does not exist in the file,then “NO NAME” is returned.*/

    public String getName(int year, int rank, String gender) {
        String na = "";
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        int number = 0;
        int find = 0;
        String finalname = null;
        for (CSVRecord record : parser) {
            if (record.get(1).equals(gender)) {
                number += 1;
                if (number == rank) {
                    finalname = record.get(0);
                    rank = 1;
                }
            }
        }
        if (find == 1) return finalname;
        else return "NO NAME";
    }


    public void testgetName() {
        String name = getName(2012, 100, "F");
        System.out.println(name);
    }





/*  What would your name be if you were born in a different year? Write the void method named
    whatIsNameInYear that has four parameters: a string name ,an integer named year representing
    the year that name was born, an integer named newYear and a string Java Programming: Solving
    Problems with Software named gender (F for female and M for male).This method determines what
    name would have been named if they were born in a different year, based on the same popularity.
    That is, you should determine the rank of name in the year they were born, and then print the
    name born in newYear that is at the same rank and same gender. For example, using the files
    "yob2012short.csv" and "yob2014short.csv", notice that in 2012 Isabella is the third most
    popular girl's name. If Isabella was born in 2014 instead, she would have been named Sophia,
    the third most popular girl's name that year. The output might look like this:
    Isabella born in 2012 would be Sophia if she was born in 2014.*/


    public void whatisNameInYear(String name, int year, int newyear, String gender) {
        String nameyear = "yob" + year + "short.csv";
        FileResource fr = new FileResource(nameyear);
        CSVParser parserold = fr.getCSVParser(false);

        String namenewyear = "yob" + newyear + "short.csv";
        FileResource fr1 = new FileResource(namenewyear);
        CSVParser parsernew = fr1.getCSVParser(false);

        int rank = 0;
        int find = 0;
        for (CSVRecord record : parserold) {
            if (record.get(1).equals(gender)) {
                rank += 1;
                if (record.get(0).equals(name)) {
                    find = 1;
                    break;
                }
            }
        }

        int ranknew = 0;
        int findnew = 0;
        if (find == 0) System.out.println("NO NAME!");
        else {
            for (CSVRecord record : parsernew) {
                if (record.get(1).equals(gender)) {
                    ranknew += 1;
                    if (ranknew == rank) {
                        findnew = 1;
                        System.out.println(name + " born in " + year + " would be " + record.get(0) + " if she/he was born in " + newyear);
                    }
                }
            }
            if (findnew == 0) System.out.println("No such rank in year " + newyear);
        }
    }
    public void testwhatisNameInYear() {
        whatisNameInYear("Isabella", 2012, 2014, "F");
    }




/* Write the method yearOfHighestRank that has two parameters: a string name and a string named
   gender(F for female and M for male). This method selects a range of files to process and returns
   an integer, the year with the highest rank for the name and gender. If the name and gender are
   not in any of the selected files, it should return 1. For example,calling yearOfHighestRank
   with name Mason and gender ‘M’ and selecting the three test files above results in returning
   the year 2012. That is because Mason was ranked the 2nd most popular name in 2012, ranked 4th
   in 2013 and ranked 3rd in 2014. His highest ranking was in 2012.*/


    public int yearOfHighestRank(String name,String gender) {
        DirectoryResource dr = new DirectoryResource();
        int ranktonow = 0;
        int find = 0;
        int findall = 0;
        for (File f: dr.selectedFiles()) {
            int rank = 0;
            String fname = f.getName();
            FileResource fr = new FileResource(fname);
            CSVParser parser = fr.getCSVParser(false);
            for (CSVRecord record: parser) {
                if (record.get(1).equals(gender)) {
                    rank += 1;
                    if (record.get(0).equals(name)) {
                        find = 1;
                        break;
                    }
                }
            }
            if (find == 1) {
                findall = 1;
                if (ranktonow == 0) ranktonow = rank;
                else if (ranktonow > rank) ranktonow = rank;
            }
        }
        if (findall == 0) return -1;
        else return ranktonow;
    }


    public void testyearOfHighestRank() {
    int ranktonow = yearOfHighestRank("Mason", "M");
    System.out.println("Highest rank is "+ranktonow);
}


/* Write the method getAverageRank that has two parameters: a string name , and a string named
   gender(F for female and M for male).This method selects a range of files to process and returns
   a double representing the average rank of the name and gender over the selected files.It should
   return 1.0 if the name is not ranked in any of the selected files. For example calling
   getAverageRank with name Mason and gender ‘M’ and selecting the three test files above results
   in returning 3.0, as he is rank 2 in the year 2012, rank 4 in 2013 and rank 3 in 2014.As another
   example, calling getAverageRank with name Jacob and gender ‘M’ and selecting the three test
   files above results in returning 2.66. */

    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double ranktonow = 0;
        int findall = 0;
        for (File f: dr.selectedFiles()) {
            int rank = 0;
            int find = 0;
            String fname = f.getName();
            FileResource fr = new FileResource(fname);
            CSVParser parser = fr.getCSVParser(false);
            for (CSVRecord record: parser) {
                if (record.get(1).equals(gender)) {
                    rank += 1;
                    if (record.get(0).equals(name)) {
                        find = 1;
                        break;
                    }
                }
                if (find == 1) {
                    findall+= 1;
                    ranktonow += rank;
                }
            }
        }
        if (ranktonow== 0) return 1;
        else {
            return ranktonow ;
        }
    }

    public void testgetAverageRank() {
        double average = getAverageRank("Jacob", "M");
        System.out.println("Average rank is "+average);
    }



/* Write the method getTotalBirthsRankedHigher that has three parameters: an integer named year,
   a string named name , and a string named gender (F for female and M for male). This method
   returns an integer, the total number of births of those names with the same gender and same
   year who are ranked higher than name . For example, if getTotalBirthsRankedHigher accesses the
   "yob2012short.csv" file with name set to “Ethan”, gender set to “M” and year set to 2012, then
   this method should return 15, since Jacob has 8 births and Mason has 7 births, and those are
   the only two ranked higher than Ethan. */


    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        String nameyear = "yob" + year + "short.csv";
        FileResource fr = new FileResource(nameyear);
        CSVParser parser = fr.getCSVParser(false);
        int find = 0;
        int sum = 0;
        for (CSVRecord record: parser) {
            if (record.get(1).equals(gender)) {
                if (record.get(0).equals(name)) {
                    find = 1;
                    break;
                }
                sum += Integer.parseInt(record.get(2));
            }
        }
        if (find == 1) return sum;
        else return -1;
    }


    public void testgetTotalBirthsRankedHigher() {
    int sum = getTotalBirthsRankedHigher(2012, "Ethan", "M");
    System.out.println("The total briths higher is "+sum);
}
}