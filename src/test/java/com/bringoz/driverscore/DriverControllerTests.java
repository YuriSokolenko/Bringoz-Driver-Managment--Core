package com.bringoz.driverscore;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bringoz.driverscore.controller.DriverController;
import com.bringoz.driverscore.model.Driver;
import com.bringoz.driverscore.model.DriverStatus;
import com.bringoz.driverscore.service.DriverServiceImpl;

	@RunWith(SpringRunner.class)
	@WebMvcTest(DriverController.class)
	public class DriverControllerTests {
		
		
		@MockBean
		private DriverServiceImpl driverService;
		
		@Autowired
	    private MockMvc mvc;
		
		private Driver driver = new Driver(

				1111L,"David","Rishon",29,"Jerusalem",DriverStatus.ACTIVE,LocalTime.of(8, 15, 00),LocalTime.of(22, 15, 00),true);
		
		private List <Driver> list = new ArrayList<>();
		
		private String exampleDriverJson = "{\"id\":\"1111\",\"firstName\":\"David\",\"lastName\":\"Rishon\",\"age\":\"29\",\"address\":\"Jerusalem\",\"status\":\"ACTIVE\",\"start\":\"08:15:00\",\"end\":\"22:15:00\",\"isInMapBounds\":\"true\"}";

		@Test
		public void creatingDriver_expectingOneTimeCall() throws Exception{
			Mockito.when(driverService.create(driver)).thenReturn(driver);
			
			mvc.perform(post("http://localhost:8081/driver-service/drivers").
					content(exampleDriverJson)
					.contentType(MediaType.APPLICATION_JSON)
					.characterEncoding("utf-8")
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andReturn();
			
			Mockito.verify(driverService, Mockito.times(1)).create(driver);
		}
		
		@Test
		public void callFindById_expectedOneTimeCall() throws Exception {
			
			Mockito.when(driverService.findById(Mockito.anyLong())).thenReturn(driver);
			
			mvc.perform( MockMvcRequestBuilders
				      .get("http://localhost:8081/driver-service/drivers/{id}", 1111L));
			Mockito.verify(driverService, Mockito.times(1)).findById(1111L);
		}
		
		@Test
		public void callGetAllDrivers_expectedOneTimeCall() throws Exception {
			list.add(driver);
			
			Mockito.when(driverService.findAllDrivers()).thenReturn(list);
			
			 mvc.perform( MockMvcRequestBuilders
						.get("http://localhost:8081/driver-service/drivers"))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$[0].id").value(list.get(0).getId()));
			
			Mockito.verify(driverService, Mockito.times(1)).findAllDrivers();
		}
		
		@Test
		public void getAllActiveDrivers_expectedOneActiveDriver() throws Exception {
			driver.setStatus(DriverStatus.ACTIVE);
			list.add(driver);
			
			Mockito.when(driverService.findAllActive()).thenReturn(list);
			mvc.perform( MockMvcRequestBuilders
						.get("http://localhost:8081/driver-service/drivers/status/{status}", "active"))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$[0].id").value(list.get(0).getId()))
						.andExpect(jsonPath("$[0].status").value(list.get(0).getStatus().name()));
			
			
			Mockito.verify(driverService, Mockito.times(1)).findAllActive();
		}
		
		@Test
		public void getAllDeliveringDrivers_expectedOneActiveDriver() throws Exception {
			driver.setStatus(DriverStatus.DELIVERING);
			list.add(driver);
			
			Mockito.when(driverService.findAllDelivering()).thenReturn(list);
			 mvc.perform( MockMvcRequestBuilders
						.get("http://localhost:8081/driver-service/drivers/status/{status}", "delivering"))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$[0].id").value(list.get(0).getId()))
						.andExpect(jsonPath("$[0].status").value(list.get(0).getStatus().name()));
			
			Mockito.verify(driverService, Mockito.times(1)).findAllDelivering();
		}
		
		@Test
		public void getAllInactiveDrivers_expectedOneActiveDriver() throws Exception {
			driver.setStatus(DriverStatus.INACTIVE);
			list.add(driver);
			
			Mockito.when(driverService.findAllInActive()).thenReturn(list);
			 mvc.perform( MockMvcRequestBuilders
						.get("http://localhost:8081/driver-service/drivers/status/{status}", "inactive")
						.param("status", "INACTIVE"))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$[0].id").value(list.get(0).getId()))
						.andExpect(jsonPath("$[0].status").value(list.get(0).getStatus().name()));
			
			Mockito.verify(driverService, Mockito.times(1)).findAllInActive();
		}
		
		@Test
		public void getAllAvailableDriversByTime_expectedOneActiveDriver() throws Exception {
			
			Mockito.when(driverService.findAllAvailableByTime(Mockito.any(), Mockito.any())).thenReturn(list);
			driver.setStatus(DriverStatus.ACTIVE);
			list.add(driver);
			 mvc.perform( MockMvcRequestBuilders
						.get("http://localhost:8081/driver-service/drivers/available")
						.param("start", "10:10:00")
						.param("end", "14:10:00"))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$[0].id").value(list.get(0).getId()))
						.andExpect(jsonPath("$[0].status").value(list.get(0).getStatus().name()))
						.andExpect(jsonPath("$[0].start").exists())
						.andExpect(jsonPath("$[0].end").exists());
								
			Mockito.verify(driverService, Mockito.times(1)).findAllAvailableByTime(LocalTime.of(10, 10, 00 ),LocalTime.of(14, 10, 00));
		}
		
		
		@Test
		public void callremoveById_expectedOneTimeCall() throws Exception {
			
			Mockito.when(driverService.remove(driver.getId())).thenReturn(driver);
			
			mvc.perform( MockMvcRequestBuilders
								.delete("http://localhost:8081/driver-service/drivers/{id}", driver.getId())
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON))
								.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
								
			
			Mockito.verify(driverService, Mockito.times(1)).remove(driver.getId());
		}
		
		@Test
		public void callupdate_expectedSuccessResponse() throws Exception {
			
			Mockito.when(driverService.update(driver)).thenReturn(driver);		
			
			mvc.perform( MockMvcRequestBuilders
								.put("http://localhost:8081/driver-service/drivers")
								.content(exampleDriverJson)
								.contentType(MediaType.APPLICATION_JSON))
								.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			
			Mockito.verify(driverService, Mockito.times(1)).update(driver);
			
		}
	}


