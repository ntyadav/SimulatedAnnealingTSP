package com.swarm_intelligence.tsp;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SimulatedAnnealing {
    private final List<City> cities;
    private Tour bestTour;

    public SimulatedAnnealing(File file) throws IOException {
        cities = new ArrayList<>();
        try (Scanner reader = new Scanner(new FileReader(file)).useLocale(Locale.US)) {
            int n = reader.nextInt();
            for (int i = 0; i < n; i++) {
                double x = reader.nextDouble();
                double y = reader.nextDouble();
                City city = new City(i, x, y);
                cities.add(city);
            }
        }
        List<City> cities = new ArrayList<>(this.cities);
        bestTour = new Tour(cities);
    }

    public Tour getBestTour() {
        return bestTour;
    }

    public void optimize() {
        final double INIT_TEMP = 3_000;
        final double EPS = 1e-8;
        final double DELTA = 0.98;
        final int STATE_ITERS = 10_000;
        Tour curTour = bestTour;
        double curTemp = INIT_TEMP;
        Random random = new Random();
        while (curTemp > EPS) {
            for (int i = 0; i < STATE_ITERS; i++) {
                Tour newTour = getNext(curTour);
                double diff = curTour.getLength() - newTour.getLength();
                if (diff > 0 || (Math.exp(diff / curTemp) > random.nextDouble())) {
                    curTour = newTour;
                }
                if (curTour.getLength() < bestTour.getLength()) {
                    bestTour = curTour;
                }
                curTemp *= DELTA;
            }
        }
    }

    private Tour getNext(Tour tour) {
        Random random = new Random();
        int c1 = 0;
        int c2 = 0;
        while (c1 == c2) {
            c1 = (int) (cities.size() * random.nextDouble());
            c2 = (int) (cities.size() * random.nextDouble());
        }
        return Tour.swapCities(tour, c1, c2);
    }
}
