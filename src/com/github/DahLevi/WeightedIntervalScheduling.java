package com.github.DahLevi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WeightedIntervalScheduling {

    List<Schedule> sortedSchedules = new ArrayList<>();
    int[] memoization = new int[0];
    int[] p = new int[0];

    public WeightedIntervalScheduling(File file) throws FileNotFoundException {
        if (readFromFile(file)) {
            Collections.sort(sortedSchedules);
            p = p(sortedSchedules.size());
            memoization = new int[sortedSchedules.size()];
            p = new int[sortedSchedules.size()];
            memoization[0] = sortedSchedules.get(0).getWeight();
            memoizationComputeOpt(sortedSchedules.size()-1);
            printResults();
        }
    }



    private boolean readFromFile(File file)  {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Not a valid file!");
            return false;
        }

        while (scanner.hasNext()){
            //String scannedText = scanner.next();
            String[] times = scanner.nextLine().split(",");
            sortedSchedules.add(new Schedule(Integer.parseInt(times[0]),
                    Integer.parseInt(times[1]),
                    Integer.parseInt(times[2])));
        }
        return true;
    }

    private int[] p(int index){
        int[] p = new int[sortedSchedules.size()];
        int currentSchedule = index - 1;
        int compareToPreviousSchedule = index - 2;
        while (currentSchedule >= 0){
            //Don't go to a location outside of the indeces of
            if(sortedSchedules.get(currentSchedule).getStartTime() <= 0 || compareToPreviousSchedule < 0){
                p[currentSchedule] = -1;
                currentSchedule--;
                compareToPreviousSchedule--;
            } else if (sortedSchedules.get(currentSchedule).getStartTime() >= sortedSchedules.get(compareToPreviousSchedule).getEndTime()){
                p[currentSchedule] = compareToPreviousSchedule;
                currentSchedule--;
                compareToPreviousSchedule--;
            } else {
                compareToPreviousSchedule--;
            }
        }
        return p;
    }



    public int memoizationComputeOpt(int j){
        if (j == 0)
            return 0;
        else if (memoization[j] != 0){
            return memoization[j];
        } else {
            memoization[j] = Math.max(sortedSchedules.get(j).getWeight() + memoizationComputeOpt(p[j]), memoizationComputeOpt(j-1));
            memoization[j-1] = sortedSchedules.get(j-1).getWeight() + memoizationComputeOpt(p[j-1]);
        }
        return memoization[j];
    }

    public void calculateSchedule(int index){
        if (index > 0) {
            if(sortedSchedules.get(index).getWeight() + memoization[p[index]] > memoization[index-1]){
                System.out.println("At index: " + index + " Start Time: " + sortedSchedules.get(index).getStartTime() + " End Time " + sortedSchedules.get(index).getEndTime() + " Weight: " + sortedSchedules.get(index).getWeight());
                calculateSchedule(p[index]); // Go back through the p
            } else {
                calculateSchedule(index - 1);
            }
        }
    }

    public void printResults(){
        System.out.println("Loading CSV file...");
        System.out.println("Reading Start Times... End times... weights....");
        System.out.println("Read the following Values:");
        System.out.printf("%5s", "Start Time");
        System.out.printf("%10s", "End Time");
        System.out.printf("%10s", "Weight");

        for (Schedule schedule: sortedSchedules) {
            System.out.println();
            String startTime = String.format("%5o", schedule.getStartTime());
            String endTime = String.format("%10o", schedule.getEndTime());
            String weight = String.format("%12o", schedule.getWeight());
            System.out.print(startTime);
            System.out.print(endTime);
            System.out.print(weight);
        }

        System.out.println();
        System.out.println("Optimal Jobs: ");
        calculateSchedule(sortedSchedules.size() - 1);
    }
}
