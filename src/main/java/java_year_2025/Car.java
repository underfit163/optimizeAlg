package java_year_2025;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Car {
    private String brand;
    private String model;


    public Map<String, List<Car>> toBrandMap(List<Car> cars) {
        Map<String, List<Car>> carsMap = new HashMap<>();

        for (Car car : cars) {
            List<Car> brandCars = carsMap.getOrDefault(car.brand, new ArrayList<>());
            brandCars.add(car);
            carsMap.put(car.brand, brandCars);
        }

        return carsMap;
    }

    public Map<String, List<Car>> toBrandMap2(List<Car> cars) {
      return cars.stream().collect(Collectors.groupingBy(e -> e.brand, Collectors.toList()));
    }
}
