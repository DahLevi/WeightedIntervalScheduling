package com.github.DahLevi;

public class Schedule implements Comparable<Schedule>{
    private int startTime;
    private int endTime;
    private int weight;

    public Schedule(int startTime, int endTime, int weight) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.weight = weight;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Schedule schedule){
        return Integer.compare(getWeight(), schedule.getWeight());
    }
}
