package com.efrei.JPAExample;

import javax.persistence.Entity;

@Entity
public class Car extends Vehicule {

    public Car() {
    }

    public Car(String plateNumber) {
        super(plateNumber);
    }

    @Override
    public String toString() {
        return "Car{} " + super.toString();
    }

}
