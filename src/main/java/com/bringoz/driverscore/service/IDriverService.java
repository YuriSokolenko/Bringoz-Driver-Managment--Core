package com.bringoz.driverscore.service;

import java.time.LocalTime;
import java.util.List;

import com.bringoz.driverscore.exception.GeneralException;
import com.bringoz.driverscore.model.Driver;

public interface IDriverService {

	public Driver create (Driver driver) throws GeneralException;
	public Driver remove (long driverId) throws GeneralException;
	public Driver update (Driver driver) throws GeneralException;
	public Driver findById (Long id) throws GeneralException;
	
	public List<Driver> findAllDrivers();
	public List<Driver> findAllActive();
	public List<Driver> findAllDelivering();
	public List<Driver> findAllInActive();
	public List<Driver> findAllAvailableByTime(LocalTime startTime, LocalTime endTime);
	
	
	
	
}
