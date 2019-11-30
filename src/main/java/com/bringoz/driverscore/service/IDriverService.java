package com.bringoz.driverscore.service;

import java.time.LocalTime;
import java.util.List;

import com.bringoz.driverscore.exception.Driverscore;
import com.bringoz.driverscore.model.Driver;

public interface IDriverService {

	public void create (Driver driver) throws Driverscore;
	public void remove (long driverId);
	public void update (Driver driver) throws Driverscore;
	public Driver findById (Long id) throws Driverscore;
	public void changeStatusToActive(Long driverId) throws Driverscore;
	public void changeStatusToInactive(Long driverId) throws Driverscore;
	public void changeStatusToDelivering(Long driverId) throws Driverscore;
	
	public List<Driver> findAllDrivers();
	public List<Driver> findAllActive();
	public List<Driver> findAllDelivering();
	public List<Driver> findAllInActive();
	public List<Driver> findAllAvailableByTime(LocalTime startTime, LocalTime endTime);
	
	
	
	
}
