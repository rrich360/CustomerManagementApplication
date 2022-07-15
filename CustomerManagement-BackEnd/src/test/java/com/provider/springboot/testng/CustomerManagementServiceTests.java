package com.provider.springboot.testng;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.provider.springboot.api.Subscriber;
import com.provider.springboot.service.SubscriberService;
import com.provider.springboot.service.SubscriberServiceImpl;
import com.provider.springboot.entity.SubscriberImpl;
import com.provider.springboot.repository.SubscriberRepository;

@ActiveProfiles("test")
@SpringBootTest(classes = { SubscriberService.class, SubscriberServiceImpl.class, CustomerManagementMocksConfig.class })
public class CustomerManagementServiceTests extends AbstractTestNGSpringContextTests {  
	
	@Autowired
	SubscriberRepository subscriberRepository;
	
	@Autowired
	SubscriberService subscriberService;
	
	@BeforeClass
	public void setup() {
        MockitoAnnotations.openMocks(this);
    }
	
	
	
	@Test
	public void givenPreSavedSubscriber_whenTraversedUsingValidId_theSubscriberShouldBeFound() {
		SubscriberImpl subscriber = createSampleSubscriberImpl();
		when(subscriberRepository.getById(anyLong())).thenReturn(subscriber);

		Subscriber fromService = subscriberService.findById(1L);
		assertEquals(SubscriberImpl.convert(fromService), subscriber);
		
		verify(subscriberRepository).getById(1L);
	}
	

	
	@Test
	public void givenPreSavedSubscriber_whenTraversedUsingValidUsername_thenSubscriberShouldBeFound() {
		SubscriberImpl subscriber = createSampleSubscriberImpl();
		when(subscriberRepository.findByUsername(anyString())).thenReturn(subscriber);
		
		Subscriber fromService = subscriberService.findByUsername("test");
		assertEquals(SubscriberImpl.convert(fromService), subscriber);
	}
	
	@Test
	public void givenValidInputFields_whenSaveButtonIsClicked_thenSubscriberShouldBeSaved() {
		SubscriberImpl subscriber = createSampleSubscriberImpl();
		when(subscriberRepository.save(any())).thenReturn(subscriber);
		
		Subscriber fromService = subscriberService.saveUser(subscriber);
		assertEquals(SubscriberImpl.convert(fromService), subscriber);

		verify(subscriberRepository).save(subscriber);
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "No subscriber provided to save")
	public void givenNullSubscriber_whenSaveButtonIsClicked_thenErrorShouldBeThrown() {
		subscriberService.saveUser(null);
	}
	
	@Test
	public void givenPreSavedSubscriber_whenUserIsEditedAndSaved_thenSubscriberShouldBeUpdated() {
		SubscriberImpl subscriber = createSampleSubscriberImpl();
		SubscriberImpl updatedSubscriber = new SubscriberImpl();
		updatedSubscriber = createSampleSubscriberImpl();
		updatedSubscriber.setUsername(updatedSubscriber.getUsername() + " subscriber updated");
		
		when(subscriberRepository.save(any())).thenReturn(subscriber, updatedSubscriber);
		when(subscriberRepository.getById(anyLong())).thenReturn(subscriber);
		
		Subscriber savedUser = subscriberService.saveUser(subscriber);
		savedUser.setUsername(savedUser.getUsername() + " updated");
		
		Subscriber fromService = subscriberService.updateUser(subscriber);
		assertNotNull(fromService);
		assertEquals(fromService.getId(), 1L);
		assertEquals(fromService.getUsername(), "test updated");
		assertEquals(fromService.getAddress(), "test1");
		assertEquals(fromService.getEmail(), "roger.richards07@gmail.com");
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "No subscriber is provided to update")
	public void givenNullSubscriber_whenAttemptingToUpdateSubscriber_thenErrorShouldBeThrown() {
		subscriberService.updateUser(null);
	}
	
	@Test
	public void givenValidId_whenDeletedWithId_thenSubscriberShouldBeDeleted() {
		doNothing().when(subscriberRepository).deleteById(anyLong());
		subscriberService.deleteUserById(1L);
		verify(subscriberRepository).deleteById(1L);
	}
	
	@Test(expectedExceptions = {Exception.class}, expectedExceptionsMessageRegExp = "No subscriber id is provided")
	public void givenNullSubscriber_whenAttemptingToDelete_thenErrorShouldBeThrown() {
		subscriberService.deleteUserById(null);
	}
	
	@Test
	public void givenPreSavedSubscribers_whenFindAllUsersIsCalled_thenListOfSubscribersIsReturned() {
		SubscriberImpl subscriber = createSampleSubscriberImpl();
		List<SubscriberImpl> usersList = new ArrayList<SubscriberImpl>();
		usersList.add(subscriber);
		when(subscriberRepository.save(any())).thenReturn(subscriber);
		when(subscriberRepository.findAll()).thenReturn(usersList);
		
		subscriberService.saveUser(subscriber);
		List<Subscriber> users = subscriberService.findAllUsers();
		assertNotNull(users);
		assertEquals(users.size(), 1);
		assertEquals(users.get(0).getUsername(), "test");
	}
	
	@Test
	public void givenPreSavedSubscriber_whenSubscriberExistsCalled_thenShouldReturnTrue() {
		SubscriberImpl subscriber = createSampleSubscriberImpl();
		when(subscriberRepository.save(any())).thenReturn(subscriber);
		when(subscriberRepository.findByUsername(anyString())).thenReturn(subscriber);
		
		Subscriber fromService = subscriberService.findByUsername("test");
		assertEquals(SubscriberImpl.convert(fromService), subscriber);
		
		boolean doesUserExist = subscriberService.isUserExist(subscriber);
		assertTrue(doesUserExist);
	}
	
	
	@Test
	public void givenUnsavedSubscriber_whenSubscriberExistsCalled_thenShouldReturnFalse() {
		SubscriberImpl subscriber = createSampleSubscriberImpl();
		SubscriberImpl subscriber2 = createSampleSubscriberImpl();
		subscriber2.setUsername("test2");
		when(subscriberRepository.save(any())).thenReturn(subscriber);
		when(subscriberRepository.findByUsername(anyString())).thenReturn(null);

		Subscriber fromService = subscriberService.findByUsername("test2");
		assertEquals(SubscriberImpl.convert(fromService), null);

		boolean doesUserExist = subscriberService.isUserExist(subscriber2);
		assertFalse(doesUserExist);
	}
	
	
	//check constructor in SubscriberImpl, line 35        
	public SubscriberImpl createSampleSubscriberImpl() {
		SubscriberImpl subscriber = new SubscriberImpl();
		subscriber.setId(1L);
		subscriber.setUsername("test");  
		subscriber.setAddress("test1");
		subscriber.setEmail("roger.richards07@gmail.com");
		return subscriber;
	}
	
	
}