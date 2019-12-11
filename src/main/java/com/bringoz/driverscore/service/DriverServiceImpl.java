package com.bringoz.driverscore.service;

import java.time.LocalTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bringoz.driverscore.HibernateConf;
import com.bringoz.driverscore.exception.GeneralException;
import com.bringoz.driverscore.model.Driver;
import com.bringoz.driverscore.repository.DriverRepository;
import com.bringoz.driverscore.utils.DriverLocation;
import com.bringoz.driverscore.utils.MapBounds;

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
		if(!(driverRepository.existsByDriverId(driver.getDriverId()))) {
			throw new GeneralException("Driver with this ID: " + driver.getDriverId() + " not found");
			} else {
				
					final Session session = HibernateConf.getHibernateSession();

					Driver updatedDriver = driverRepository.findByDriverId(driver.getDriverId()).get();
					updatedDriver.setAddress(driver.getAddress());
					updatedDriver.setAge(driver.getAge());
					updatedDriver.setIsInMapBounds(driver.getIsInMapBounds());
					updatedDriver.setStart(driver.getStart());
					updatedDriver.setEnd(driver.getEnd());
					updatedDriver.setStatus(driver.getStatus());
					
					try {
						Transaction tx = session.beginTransaction();
						session.merge(updatedDriver);
						tx.commit();
						session.clear();
					
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						session.close();
					}
					return updatedDriver;
					}
	}

	@Cacheable("driver")
	@Override
	public Driver findById(Long id) throws GeneralException {
		if(driverRepository.existsByDriverId(id)) {
			
			return driverRepository.findByDriverId(id).get();
		} else {
				throw new GeneralException("Driver with id "+id + " not found");
				}
	}

	@Override
	public List<Driver> findAllAvailableByTime(LocalTime startTime, LocalTime endTime){
		return driverRepository.findAllAvailable(startTime, endTime);
	}
	
	@Cacheable("driver")
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

	public Driver setLocationById(long id, DriverLocation driverLocation) {
		Driver driver = driverRepository.findByDriverId(id).get();
		MapBounds mapBounds = new MapBounds(MapBounds.TA_MAX_NORTH_LATITUDE,
											MapBounds.TA_MAX_SOUTH_LATITUDE,
											MapBounds.TA_MAX_WEST_LONGITUDE,
											MapBounds.TA_MAX_EAST_LONGITUDE);
		driver.setInMapBounds(mapBounds.ifInMapBounds(driverLocation.getLatitude(),
													  driverLocation.getLongitude()));
		
		final Session session = HibernateConf.getHibernateSession();
		
				try {
					Transaction tx = session.beginTransaction();
					session.merge(driver);
					tx.commit();
					session.clear();
				
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					session.close();
				}
				
			return driver;
			}

}
