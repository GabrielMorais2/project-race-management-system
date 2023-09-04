package com.moraes.gabriel.mscars.service;

import com.moraes.gabriel.mscars.exception.CarAlreadyExistsException;
import com.moraes.gabriel.mscars.exception.CarNotFoundException;
import com.moraes.gabriel.mscars.exception.PilotAlreadyExistsException;
import com.moraes.gabriel.mscars.model.Car;
import com.moraes.gabriel.mscars.model.payload.CarRequest;
import com.moraes.gabriel.mscars.model.payload.CarResponse;
import com.moraes.gabriel.mscars.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final ModelMapper mapper;

    public CarResponse createCar(CarRequest carRequest) {
        Car car = mapper .map(carRequest, Car.class);
        validateCarAndPilot(car);
        return mapper.map(carRepository.save(car), CarResponse.class);

    }

    public CarResponse getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found with id: " + id));
        return mapper.map(car, CarResponse.class);
    }

    public List<CarResponse> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(instructor -> mapper.map(instructor, CarResponse.class))
                .collect(Collectors.toList());

    }

    private void validateCarAndPilot(Car car) {
        if (isCarExists(car.getBrand(), car.getModel(), car.getYear())) {
            throw new CarAlreadyExistsException("There is already a corresponding car registered.");
        }

        if (isPilotExists(car.getPilot().getName(), car.getPilot().getAge())) {
            throw new PilotAlreadyExistsException("There is already a corresponding Pilot registered.");
        }
    }

    private boolean isCarExists(String brand, String model, String year) {
        return carRepository.existsByBrandAndModelAndYear(brand, model, year);
    }

    private boolean isPilotExists(String name, Integer age) {
        return carRepository.existsByPilotNameAndPilotAge(name, age);
    }
}
