package com.github.DahLevi;

import java.io.*;

public class Main {



    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Please enter a file location: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        File file = null;
        try {
            file = new File(bufferedReader.readLine());
        } catch (IOException e) {
            System.out.println("Not a valid file!");
            return;
        }
        WeightedIntervalScheduling weights = new WeightedIntervalScheduling(file);
    }




}
