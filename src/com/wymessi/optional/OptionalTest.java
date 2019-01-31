package com.wymessi.optional;

import java.util.Optional;

/**
 * Created by wy on 2019/1/31
 */
public class OptionalTest {
    
    public static void main(String[] args) {
        Person p = new Person();
        Car c = new Car();
        Insurance i = new Insurance();
        Optional<Person> optionalPerson = Optional.ofNullable(p);
        Optional<Car> optionalCar = Optional.ofNullable(c);
        Optional<Insurance> optionalInsurance = Optional.ofNullable(i);
        p.setCar(optionalCar);
        c.setInsurance(optionalInsurance);
        String name = getCarInsuranceName(optionalPerson);
        System.out.println(name);
    }


    public static String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
}
