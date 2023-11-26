package com.mcancankaya.vehiclefactory.repositories;

import com.mcancankaya.vehiclefactory.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {

}
