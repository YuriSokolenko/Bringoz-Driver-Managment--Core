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
	public String toString() {
		return "Driver [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", address=" + address + ", status=" + status + ", start=" + start + ", end=" + end
				+ ", isInMapBounds=" + isInMapBounds + "]";
	}

	
}
