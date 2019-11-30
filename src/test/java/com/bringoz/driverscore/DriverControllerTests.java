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
import org.springframework.test.web.servlet.MvcResult;
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
		public void creatingDriver_expectingReturnsString() throws Exception{
			
			Mockito.doNothing().when(driverService).create(driver);
			
			MvcResult result = mvc.perform(post("http://localhost:8081/driver-service/createDriver").
					content(exampleDriverJson)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			
			String response = result.getResponse().getContentAsString();
			assertEquals("New Driver has been saved", response);
		}
		
		@Test
		public void callFindById_expectedOneTimeCall() throws Exception {
			
			Mockito.when(driverService.findById(Mockito.anyLong())).thenReturn(driver);
			
			mvc.perform( MockMvcRequestBuilders
				      .get("http://localhost:8081/driver-service/getDriverById/{id}", 1111L));
			Mockito.verify(driverService, Mockito.times(1)).findById(1111L);
		}
		
		@Test
		public void callGetAllDrivers_expectedOneTimeCall() throws Exception {
			list.add(driver);
			
			Mockito.when(driverService.findAllDrivers()).thenReturn(list);
			
			 mvc.perform( MockMvcRequestBuilders
						.get("http://localhost:8081/driver-service/getAllDrivers"))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$[0].id").value(list.get(0).getId()));
			
			Mockito.verify(driverService, Mockito.times(1)).findAllDrivers();
		}
		
		@Test
		public void getAllActiveDrivers_expectedOneActiveDriver() throws Exception {
			
			Mockito.when(driverService.findAllActive()).thenReturn(list);
			
			list.add(driver);
			 mvc.perform( MockMvcRequestBuilders
						.get("http://localhost:8081/driver-service/getAllActiveDrivers"))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$[0].id").value(list.get(0).getId()))
						.andExpect(jsonPath("$[0].status").value(list.get(0).getStatus().name()));
			
			Mockito.verify(driverService, Mockito.times(1)).findAllActive();
		}
		
		@Test
		public void getAllDeliveringDrivers_expectedOneActiveDriver() throws Exception {
			
			Mockito.when(driverService.findAllDelivering()).thenReturn(list);
			driver.setStatus(DriverStatus.DELIVERING);
			list.add(driver);
			 mvc.perform( MockMvcRequestBuilders
						.get("http://localhost:8081/driver-service/getAllDeliveringDrivers"))
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(content().contentType(MediaType.APPLICATION_JSON))
						.andExpect(jsonPath("$[0].id").value(list.get(0).getId()))
						.andExpect(jsonPath("$[0].status").value(list.get(0).getStatus().name()));
			
			Mockito.verify(driverService, Mockito.times(1)).findAllDelivering();
		}
		
		@Test
		public void getAllInactiveDrivers_expectedOneActiveDriver() throws Exception {
			
			Mockito.when(driverService.findAllInActive()).thenReturn(list);
			driver.setStatus(DriverStatus.INACTIVE);
			list.add(driver);
			 mvc.perform( MockMvcRequestBuilders
						.get("http://localhost:8081/driver-service/getAllInactiveDrivers"))
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
						.post("http://localhost:8081/driver-service/availableDriver")
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
		public void callChangeStatusToDelivering_expectedOneTimeCall() throws Exception {
			
			Mockito.doNothing().when(driverService).changeStatusToDelivering(1111L);
			
			MvcResult result =  mvc.perform( MockMvcRequestBuilders
				      			.post("http://localhost:8081/driver-service/changeStatusToDelivering/{id}", 1111L))
								.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			
			String response = result.getResponse().getContentAsString();
			
			assertEquals("Driver status changed to DELIVERING", response);
			Mockito.verify(driverService, Mockito.times(1)).changeStatusToDelivering(1111L);
		}
		
		@Test
		public void callChangeStatusToActive_expectedOneTimeCall() throws Exception {
			
			Mockito.doNothing().when(driverService).changeStatusToActive(1111L);
			
			MvcResult result =   mvc.perform( MockMvcRequestBuilders
								.post("http://localhost:8081/driver-service/changeStatusToActive/{id}", 1111L))
								.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			
			String response = result.getResponse().getContentAsString();
			
			assertEquals("Driver status changed to ACTIVE", response);;
			Mockito.verify(driverService, Mockito.times(1)).changeStatusToActive(1111L);
		}
		
		@Test
		public void callChangeStatusToInActive_expectedOneTimeCall() throws Exception {
			
			Mockito.doNothing().when(driverService).changeStatusToInactive(1111L);
			
			MvcResult result = mvc.perform( MockMvcRequestBuilders
								.post("http://localhost:8081/driver-service/changeStatusToInactive/{id}", 1111L))
								.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			
			String response = result.getResponse().getContentAsString();
			
			assertEquals("Driver status changed to INACTIVE", response);
			
			Mockito.verify(driverService, Mockito.times(1)).changeStatusToInactive(1111L);
		}
		
		@Test
		public void callremoveById_expectedOneTimeCall() throws Exception {
			
			Mockito.doNothing().when(driverService).remove(1111L);
			
			MvcResult result =  mvc.perform( MockMvcRequestBuilders
								.delete("http://localhost:8081/driver-service/removeDriver/{id}", 1111L))
								.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			
			String response = result.getResponse().getContentAsString();
			
			assertEquals("Driver with id: " + 1111L + " has been removed", response);;
			Mockito.verify(driverService, Mockito.times(1)).remove(1111L);
		}
		
		@Test
		public void callupdate_expectedSuccessResponse() throws Exception {
			
			Mockito.doNothing().when(driverService).update(driver);
			
			MvcResult result =  mvc.perform( MockMvcRequestBuilders
								.post("http://localhost:8081/driver-service/updateDriver")
								.content(exampleDriverJson)
								.contentType(MediaType.APPLICATION_JSON))
								.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			
			
			String response = result.getResponse().getContentAsString();
			
			assertEquals("Driver with id: " + driver.getId() + " successfully updated ", response);;
		}
		
		
		
		
		
		
	}


