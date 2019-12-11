package com.bringoz.driverscore.model;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity

public class Driver implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6129788184602878279L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ID", updatable = false, nullable = false)
	private long id;

	@Column(name = "driver_id", updatable = false, nullable = false)
	private Long driverId;
	
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;
	@Column(name = "AGE", nullable = false)
	private int age;
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private DriverStatus status;
	
	@Column(name = "START_TIME", columnDefinition = "TIME")
	private LocalTime start;
	
	@Column(name = "END_TIME", columnDefinition = "TIME")
	private LocalTime end;
	
	@Column(name = "IS_IN_MAP_BOUNDS")
	private boolean isInMapBounds;

	public Driver() {
		super();
	}


	public Driver(Long driverId, String firstName, String lastName, int age, String address,
			DriverStatus status, LocalTime start, LocalTime end, boolean isInMapBounds) {
		super();
		this.driverId = driverId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.address = address;
		this.status = status;
		this.start = start;
		this.end = end;
		this.isInMapBounds = true;
	}

	

	public Long getDriverId() {
		return driverId;
	}


	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setInMapBounds(boolean isInMapBounds) {
		this.isInMapBounds = isInMapBounds;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public DriverStatus getStatus() {
		return status;
	}

	public void setStatus(DriverStatus status) {
		this.status = status;
	}


	public LocalTime getStart() {
		return start;
	}

	public void setStart(LocalTime start) {
		this.start = start;
	}

	public LocalTime getEnd() {
		return end;
	}

	public void setEnd(LocalTime end) {
		this.end = end;
	}

	public Long getId() {
		return driverId;
	}

	public void setId(Long id) {
		this.driverId = id;
	}

	public void setIsInMapBounds(boolean isInMapBounds) {
		this.isInMapBounds = isInMapBounds;
	}
	public boolean getIsInMapBounds() {
		return isInMapBounds;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + age;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((driverId == null) ? 0 : driverId.hashCode());
		result = prime * result + (isInMapBounds ? 1231 : 1237);
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age != other.age)
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (driverId == null) {
			if (other.driverId != null)
				return false;
		} else if (!driverId.equals(other.driverId))
			return false;
		if (isInMapBounds != other.isInMapBounds)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Driver [id=" + driverId + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", address=" + address + ", status=" + status + ", start=" + start + ", end=" + end
				+ ", isInMapBounds=" + isInMapBounds + "]";
	}

	
}
