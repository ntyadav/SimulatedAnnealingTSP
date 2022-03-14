package com.swarm_intelligence.tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tour {
    private final List<City> cityPath;
    private final double length;

    public List<City> getCityPath() {
        return cityPath;
    }

    public double getLength() {
        return length;
    }

    public Tour(List<City> cityPath) {
        this.cityPath = Collections.unmodifiableList(cityPath);
        this.length = calculateLength(cityPath);
    }

    public static Tour swapCities(Tour tour, int cityIndex1, int cityIndex2) {
        List<City> newCityPath = new ArrayList<>(tour.cityPath);
        City tmp = newCityPath.get(cityIndex1);
        newCityPath.set(cityIndex1, tour.cityPath.get(cityIndex2));
        newCityPath.set(cityIndex2, tmp);
        return new Tour(newCityPath);
    }

    private static double calculateLength(List<City> cities) {
        double res = 0;
        for (int i = 1; i < cities.size(); i++) {
            City city1 = cities.get(i - 1);
            City city2 = cities.get(i);
            res += getDistance(city1, city2);
        }
        res += getDistance(cities.get(cities.size() - 1), cities.get(0));
        return res;
    }

    private static double getDistance(City city1, City city2) {
        return Math.sqrt(Math.pow(city1.x - city2.x, 2) + Math.pow(city1.y - city2.y, 2));
    }
}
