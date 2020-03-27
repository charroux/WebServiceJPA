package com.efrei.JPAExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class RestWebService {

	private static final Logger log = LoggerFactory.getLogger(RestWebService.class);

	PersonRepository personRepository;
	CarRepository carRepository;

	@Autowired
	public RestWebService(PersonRepository personRepository, CarRepository carRepository) {
		super();
		this.personRepository = personRepository;
		this.carRepository = carRepository;
	}
	
	@GetMapping("/persons")
	public Iterable<Person> getPersons(){
		return personRepository.findAll();
	}

	@PostMapping("/persons")
	public void addPerson(@RequestBody Person person) throws Exception {
		personRepository.save(person);
	}

	@GetMapping("/vehicules")
	public Iterable<Car> getVehicules(){
		return carRepository.findAll();
	}

	@PostMapping("/vehicules")
	public void addVehicule(@RequestBody Car car) throws Exception {
		carRepository.save(car);
	}

	@PutMapping("/vehicules/{plateNumber}/{personName")
	public void rent(@PathVariable("plateNumber") String plateNumber,
					 @PathVariable("personName") String personName,
					 @RequestBody Dates dates) throws Exception{
		
		List<Car> cars = carRepository.findByPlateNumber(plateNumber);
		Car car = cars.get(0);

		log.info(car.toString());

		List<Person> persons = personRepository.findByName(personName);
		Person person = persons.get(0);

		log.info(person.toString());

		Rent rent = new Rent();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(dates.getBegin());
		rent.setBeginDate(date);
		date = dateFormat.parse(dates.getEnd());
		rent.setEndDate(date);

		person.getRents().add(rent);
		rent.setPerson(person);;

		car.getRents().add(rent);
		rent.setVehicule(car);

		log.info(rent.toString());

		personRepository.save(person);

	}

}
