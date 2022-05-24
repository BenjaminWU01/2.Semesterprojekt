package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Order;
import model.OrderLine;
import model.Product;
import model.Size;
import model.Contact;

public class OrderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void Calling_ConstructorWithNoParameters_ShouldCreate_ANewOrderObject() {
		//Arrange
		Order testOrder;
		
		//Act
		testOrder = new Order();
		
		//Assert
		assertTrue("Order constructor did not create order object", testOrder instanceof Order);
	}

	@Test
	public void Calling_getOrderLines_ShouldReturn_AnArrayListOfTypeOrderLine() {
		//Arrange
		Order testOrder;
				
		//Act
		testOrder = new Order();
				
		//Assert
		assertTrue("Instance variable 'orderLines' is not an ArrayList", testOrder.getOrderLines() instanceof ArrayList);
	}

	@Test
	public void Calling_addOrderLineWithOrderLineObject_ShouldAdd_OrderLineObjectToArrayList() {
		//Arrange
		Order testOrder = new Order();
		
		int testIdSize = 1;
		Size testSize = new Size("testSize", testIdSize);
		
		int testIdProduct = 4444;
		Product testProduct = new Product("testProdNo", "testProdDesc", testSize, testIdProduct);
		
		int testQuantity = 4;
		OrderLine testOrderLine = new OrderLine(testProduct, testQuantity);
		
		int expectedSize = 1;
		int actualSize;
		
		//Act
		testOrder.addOrderLine(testOrderLine);
		actualSize = testOrder.getOrderLines().size();
		
		//Assert
		assertEquals("Adding 1 OrderLine object to empty 'orderLines' List did not work as expected", expectedSize, actualSize);
	}

	@Test
	public void Calling_addCustomerWithCustomerObject_ShouldAdd_CustomerObjectToOrderObject() {
		//Arrange
		Order testOrder = new Order();
		
		int testIdContact = 5555;
		Contact testCustomer = new Contact("testName", "testAddress", "testZip", "testCountry", "testCity", "testPhoneNo", "testEmail", testIdContact);
				
		//Act
		testOrder.addCustomer(testCustomer);
				
		//Assert
		assertTrue("", testOrder.getContact() instanceof Contact);
	}

}
