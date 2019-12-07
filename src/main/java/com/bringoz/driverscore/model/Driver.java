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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;

import ch.qos.logback.core.status.Status;


@Entity

public class Driver implements Serializable {
	
	
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private int age;
	@Column
	private String address;
	
	@Column
	@Enumerated(EnumType.STRING)
	private DriverStatus status;
	
	@Column(name = "start_time", columnDefinition = "TIME")
	private LocalTime start;
	
	@Column(name = "end_time", columnDefinition = "TIME")
	private LocalTime end;
	
	@Column
	private boolean isInMapBounds;

	public Driver() {
		super();
	}

	public Driver(Long id, String firstName, String lastName, int age, String address, DriverStatus status,
			LocalTime start, LocalTime end, boolean isInMapBounds) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.address = address;
		this.status = status;
		this.start = start;
		this.end = end;
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
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return "Driver [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", address=" + address + ", status=" + status + ", start=" + start + ", end=" + end
				+ ", isInMapBounds=" + isInMapBounds + "]";
	}

	
}
