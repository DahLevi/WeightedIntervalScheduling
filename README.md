# Weighted Interval Scheduling Extra Credit Implementation
 Project Devoper: Levi Dahlberg
## Description:
Given a CSV file with lists of jobs in the format of Start-time,End-time,Duration the program will calculate the optimal jobs to be scheduled based on the start time, end time and weight of the job. This allows for strategic scheduling and optimization of time and personnel resources.

## User Manual:
- Ensure you have Java 1.8 JRE installed
- Download or clone this repo and put the jar and csv files in a folder on your desktop (For ease of access)
- Open a command prompt and enter ```java -jar scheduling.jar```
- Follow the prompt(s) and allow for scheduling to complete
- View the output on the console screen

## Details of the Implementation:
The key to this algorithm is ensuring that the schedules passed to it are sorted by end time from greatest to least. Courtesy of the Java Collections sorting method, this is done in O(nlogn) time. It uses the compareTo method of the custom Schedule class to sort by end time.

The system then computes the p time with the ```p``` method that iteratively evaluates the p value. This is done in O(n) time.

Following the computation of p, the ```memoizationComputeOpt``` Method recursively does a comparison of the index from the memoization array and sets the accumulated weight of the total jobs compatible with that p value. The comparisons are done in O(1) time but happen ```n``` times making it O(n).

In order to then print out the optimal scheduling, another recursive function is utilized ```calculateSchedule``` to reveal the compatible and also optimal jobs.

## Testing:

## Screenshots/Proof of operation
