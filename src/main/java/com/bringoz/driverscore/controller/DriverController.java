package com.bringoz.driverscore.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bringoz.driverscore.exception.GeneralException;
import com.bringoz.driverscore.model.Driver;
import com.bringoz.driverscore.service.DriverServiceImpl;


@RestController
@RequestMapping("/driver-service/")
public class DriverController {
	
	@Autowired
	private DriverServiceImpl driverService;
	
	
	@RequestMapping(value="/drivers", method= RequestMethod.POST)
	public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) throws GeneralException {
		return ResponseEntity.ok(driverService.create(driver));
	}

	@GetMapping("/drivers/{id}")
	public Driver getDriverById(@PathVariable ("id") Long id) throws GeneralException {
		return driverService.findById(id);
	}
	
	@RequestMapping(value="/drivers", method= RequestMethod.GET)
	public List<Driver> getAllDrivers() {
		return driverService.findAllDrivers();
	}
	
	@GetMapping("/drivers/status/{status}")
	public ResponseEntity<List<Driver>> getAllDriversByStatus(@PathVariable("status") String status) {
		
		switch (status) {
		case "inactive":
			return ResponseEntity.ok(driverService.findAllInActive());
			
		case "delivering":
			return ResponseEntity.ok(driverService.findAllDelivering());
			
		case "active": 
			return ResponseEntity.ok(driverService.findAllActive());
			
		default:
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value="/drivers", method=RequestMethod.PUT)
	public ResponseEntity<Driver> updateDriver(@RequestBody Driver driver) throws GeneralException{
		return ResponseEntity.ok(driverService.update(driver));
	}
	
	@RequestMapping(value="/drivers/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<Driver> removeDriverById(@PathVariable("id") long id) throws GeneralException{
		if(driverService.findById(id) != null) {
			return ResponseEntity.ok(driverService.remove(id));
		}	
		else {
			  return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/drivers/available")
	public List<Driver> getAllAvailableDriversByTime(@RequestParam("start")
									   @DateTimeFormat(pattern = "HH:mm:ss") LocalTime startTime, 
									   @RequestParam("end")
									   @DateTimeFormat(pattern = "HH:mm:ss") LocalTime endTime ) {
		return driverService.findAllAvailableByTime(startTime, endTime);
	}
	
	
}
