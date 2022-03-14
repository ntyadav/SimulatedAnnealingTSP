package com.swarm_intelligence;

import com.swarm_intelligence.tsp.City;
import com.swarm_intelligence.tsp.SimulatedAnnealing;
import com.swarm_intelligence.tsp.Tour;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("./dataset.txt");
        SimulatedAnnealing annealing = new SimulatedAnnealing(file);
        printPath(annealing.getBestTour());
        annealing.optimize();
        printPath(annealing.getBestTour());
    }

    private static void printPath(Tour tour) {
        List<City> cities = tour.getCityPath();
        for (int i = 0; i < cities.size(); i++) {
            City city = cities.get(i);
            System.out.print(city.index);
            if (i < cities.size() - 1) {
                System.out.print("—>");
            }
        }
        System.out.println();
        System.out.println("Path length: " + tour.getLength());
    }
}
