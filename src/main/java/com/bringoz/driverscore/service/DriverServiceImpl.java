package com.bringoz.driverscore.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bringoz.driverscore.exception.Driverscore;
import com.bringoz.driverscore.model.Driver;
import com.bringoz.driverscore.model.DriverStatus;
import com.bringoz.driverscore.repository.DriverRepository;

@Service
public class DriverServiceImpl implements IDriverService {
	
	@Autowired
	private DriverRepository driverRepository;

	@Override
	public void create(Driver driver) throws Driverscore {
		if(driverRepository.existsById(driver.getId())) {
			throw new Driverscore("Driver with this ID is already exist");
		} else {
		driverRepository.save(driver);
		}
	}

	@Override
	public void remove(long driverId) {
		driverRepository.deleteById(driverId);
	}

	@Override
	public void update(Driver driver) throws Driverscore {
		if(driverRepository.existsById(driver.getId())) {
			throw new Driverscore("Driver with this ID is already exist");
		} else {
		
		Driver updatedDriver = driverRepository.findById(driver.getId()).get();
		updatedDriver.setAddress(driver.getAddress());
		updatedDriver.setAge(driver.getAge());
		updatedDriver.setIsInMapBounds(driver.getIsInMapBounds());
		updatedDriver.setStart(driver.getStart());
		updatedDriver.setEnd(driver.getEnd());
		updatedDriver.setStatus(driver.getStatus());
		driverRepository.save(updatedDriver);
		}
	}

	@Override
	public Driver findById(Long id) throws Driverscore {
		if(driverRepository.existsById(id)) {
			return driverRepository.findById(id).get();
		} else {
			throw new Driverscore("Driver with id "+id + " not found");
		}
	}

	@Override
	public void changeStatusToActive(Long driverId) throws Driverscore {
		Driver driver = driverRepository.findById(driverId).get();
		if(driver!=null) {
		driver.setStatus(DriverStatus.ACTIVE);
		driverRepository.save(driver);
		} else {
			throw new Driverscore("Driver with id "+driverId + " not found");
		}
	}
	
	@Override
	public void changeStatusToInactive(Long driverId) throws Driverscore {
		Driver driver = driverRepository.findById(driverId).get();
		if(driver!=null) {
			driver.setStatus(DriverStatus.INACTIVE);
			driverRepository.save(driver);
			} else {
				throw new Driverscore("Driver with id "+driverId + " not found");
			}
	}
	
	@Override
	public void changeStatusToDelivering(Long driverId) throws Driverscore {
		Driver driver = driverRepository.findById(driverId).get();
		if(driver!=null) {
			driver.setStatus(DriverStatus.DELIVERING);
			driverRepository.save(driver);
			} else {
				throw new Driverscore("Driver with id "+driverId + " not found");
			}
	}

	@Override
	public List<Driver> findAllAvailableByTime(LocalTime startTime, LocalTime endTime){
		return driverRepository.findAllAvailable(startTime, endTime);
	}
	
	@Override
	public List<Driver> findAllDrivers() {
		return driverRepository.findAll();
	}
	
	@Override
	public List<Driver> findAllActive() {
		return driverRepository.findAllActive();
	}
	
	@Override
	public List<Driver> findAllDelivering() {
		return driverRepository.findAllDelivering();
	}

	@Override
	public List<Driver> findAllInActive() {
		return driverRepository.findAllInactive();
	}

}
