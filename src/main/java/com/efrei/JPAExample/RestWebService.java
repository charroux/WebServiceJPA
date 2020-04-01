package com.efrei.JPAExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class RestWebService {

	private static final Logger log = LoggerFactory.getLogger(RestWebService.class);

  	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Error")  
  	@ExceptionHandler(Exception.class)
  	public void error() {
  	}

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

	@PutMapping("/vehicules/{plateNumber}")
	public void rent(@PathVariable("plateNumber") String plateNumber,
		@RequestParam(value = "personName", required = true) String personName,
		@RequestBody Dates dates) throws Exception{

		System.out.println(plateNumber);
		System.out.println(personName);
			System.out.println(dates);

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
	
	@GetMapping("/frontendURL")
	public String message(){
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://localhost:8181/backendURL";
    		String result = restTemplate.getForObject(uri, String.class);
		return "Message from the front end while the backend is called: " + result;
	}

}
