package com.bringoz.driverscore.service;

import java.time.LocalTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bringoz.driverscore.HibernateConf;
import com.bringoz.driverscore.exception.GeneralException;
import com.bringoz.driverscore.model.Driver;
import com.bringoz.driverscore.repository.DriverRepository;

@Service
public class DriverServiceImpl implements IDriverService {
	
	@Autowired
	private DriverRepository driverRepository;

	@Override
	public Driver create(Driver driver) throws GeneralException {
		
		final Session session = HibernateConf.getHibernateSession();
		
		if(driverRepository.existsByDriverId(driver.getDriverId())) {
			throw new GeneralException("Driver with this ID is already exist");
		} else {
			
			try {
				Transaction tx = session.beginTransaction();
				session.save(driver);
				tx.commit();
				session.clear();
			
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				session.close();
			}
				return driverRepository.findByDriverId(driver.getDriverId()).get();
				}
	}

	@Override
	public Driver remove(long driverId) throws GeneralException {
		Driver driver = driverRepository.findById(driverId).get();
		if(driver != null) {
			driverRepository.deleteById(driverId);
			return driver;
		} else {
			throw new GeneralException("Driver with id "+ driverId + " not found");
		}
	}

	@Override
	public Driver update(Driver driver) throws GeneralException {
		if(!(driverRepository.existsById(driver.getId()))) {
			throw new GeneralException("Driver with this ID: " + driver.getId() + " not found");
			} else {
		
					Driver updatedDriver = driverRepository.findById(driver.getId()).get();
					updatedDriver.setAddress(driver.getAddress());
					updatedDriver.setAge(driver.getAge());
					updatedDriver.setIsInMapBounds(driver.getIsInMapBounds());
					updatedDriver.setStart(driver.getStart());
					updatedDriver.setEnd(driver.getEnd());
					updatedDriver.setStatus(driver.getStatus());
					driverRepository.save(updatedDriver);
					return updatedDriver;
					}
	}

	@Override
	public Driver findById(Long id) throws GeneralException {
		if(driverRepository.existsByDriverId(id)) {
			return driverRepository.findById(id).get();
		} else {
				throw new GeneralException("Driver with id "+id + " not found");
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
