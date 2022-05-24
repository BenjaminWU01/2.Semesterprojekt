package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Order;
import model.OrderLine;
import model.Product;
import model.Size;

public class OrderLineTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void Calling_ConstructorWithProductAndQuantity_ShouldCreate_ANewOrderLineObject() {
		//Arrange
		OrderLine testOrderLine;
		
		int testIdSize = 1;
		Size testSize = new Size("testSize", testIdSize);
		
		int testIdProduct = 4444;
		Product testProduct = new Product("testProdNo", "testProdDesc", testSize, testIdProduct);
		
		int testQuantity = 4;
				
		//Act
		testOrderLine = new OrderLine(testProduct, testQuantity);
				
		//Assert
		assertTrue("OrderLine constructor did not create orderline object", testOrderLine instanceof OrderLine);
	}

	@Test
	public void Calling_getProduct_ShouldReturn_ProductMatchingAddedProduct() {
		//Arrange
		OrderLine testOrderLine;
		
		int testIdSize = 1;
		Size testSize = new Size("testSize", testIdSize);
		
		int testIdProduct = 4444;
		Product testProduct = new Product("testProdNo", "testProdDesc", testSize, testIdProduct);
		
		int testQuantity = 4;
				
		testOrderLine = new OrderLine(testProduct, testQuantity);
		
		Product returnedProduct;
		
		//Act
		returnedProduct = testOrderLine.getProduct();
						
		//Assert
		assertTrue("", returnedProduct.equals(testProduct));
	}

	@Test
	public void Calling_getQuantity_ShouldReturn_QuantityMatchingAddedQuantity() {
		//Arrange
		OrderLine testOrderLine;
		
		int testIdSize = 1;
		Size testSize = new Size("testSize", testIdSize);
		
		int testIdProduct = 4444;
		Product testProduct = new Product("testProdNo", "testProdDesc", testSize, testIdProduct);
		
		int testQuantity = 4;
				
		testOrderLine = new OrderLine(testProduct, testQuantity);
		
		int returnQuantity;
		
		//Act
		returnQuantity = testOrderLine.getQuantity();
						
		//Assert
		assertEquals("",  testQuantity, returnQuantity);
	}

}
