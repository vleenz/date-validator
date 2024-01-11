# Date Validator Etude 1 - Vincent Lee

This code was tested on Windows 11 with Visual Studio Code (Version 1.76.1) using the Extension Pack for Java (Version 0.25.9).

Compile and run the program and start entering dates in the terminal in any of the following formats: 
day: dd or d or 0d  
month: mm or m or 0m or the first three letters of the month name (all in the same case,
or with the first letter upper-case)  
year: yy or yyyy  
separator: - or / or "space"
Note: only one separator type to be used in one date  
If the date is invalid, it will echo the invalid date followed by " - INVALID" as well as the reason why it is.

**Test files are included in the repository**  
dates.txt  
leapYears.txt  
testDataCorrect.txt  
testDataIncorrect.txt  




## Testing
My initial testing consisted of creating two files; one with inputs I knew to be correct and one with inputs I knew would be incorrect. From there I was able to tell if there were any obvious errors in my code. The correct one should run through the inputs smoothly, and the incorrect file would echo the incorrect input aswell as the error. I found this to be the most helpful way of testing as if an incorrect date would not return - INVALID, I could easily spot it and work out why it was passing as correct, or vice versa, if a correct input would return the - INVALID I could spot it and try and see why it was returning as invalid.  

To define whether a date was incorrect or correct, I thoroughly read through the specifications and wrote down any ways I thought that a date could pass as invalid. In my file I split up invalid cases into categories: DAY, MONTH, YEAR, and OTHER. By splitting up invalid cases into these categories, I could be much more precise in what errors I could create. 

To test Februray and my leap year code was working correctly, I created a method that would generate a list of every single leap year from 1753 to 3000. I could then use that to check Februrary was passing by using 29/02/ + the leap year as my input.

I also set up some JUnit tests, but found this to be similar to my input from a file testing. I used some tests to ensure my separators were behaving how I wanted them to. I think by splitting up my invalid file input by categories, I was pretty much just creating a test for each unit anyway.

I also tested correct dates by passing in over 450,000 different dates in multiple formats that I knew to be correct. These dates were generated using a program created by chatGPT.

To test to program and read from a file, uncomment lines 26 to 36 and comment out lines 38 to 43. The program will now read in from a file line by line. To test valid cases ensure line 27 reads `Scanner sc = new Scanner(new File("<file name here>"));`   

## References
isNumeric() method found at:  https://www.baeldung.com/java-check-string-number#:~:text=Perhaps%20the%20easiest%20and%20the

### Incorrect test cases: 
**Day**
Leading/trailing space
3 digits long  
Not a number  
Leading space  
Greather than 31  
Negative number  
Random non-letter characters  
Zero (0 or 00)

**Month**
Leading/trailing space
Each different MMM format not in same case or first letter uppercase  
31st of Feburary  
29th of Feburary not on a leap year  
If 3 units long and not in the format MMM  
4 digits long  
greater than 12  
leading white space with each of the different formats  
Incorrect spelling of 3 letter month  
Zero (0 or 00)

**Year**
Leading/trailing space  
Year non-numeric and greater than 4 digits  
Year non-numeric 4 digits  
Year non-numeric 2 digits  
Leading zeroes but still 4 digits  
3 digits long    
Numeric year 5 digits  
Greather than 30000  
Less than 1753  
Leaving it blank  
Random non-letter characters  
Zero  
 
**Other**  
More than 3 inputs  
Wrong separators  
Multiple types of separators  
More than 2 separators  
Leading spaces  
Random non-letter characters  
Putting nothing in  



