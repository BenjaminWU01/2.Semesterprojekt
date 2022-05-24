package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Product;
import model.Size;

public class ProductTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void Calling_ConstructorWith_prodNo_prodDesc_size_ShouldCreate_ANewProductObject() {
		//Arrange
		int testIdSize = 1;
		Size testSize = new Size("testSize", testIdSize);
		int testIdProduct = 4444;
		Product testProduct;
		
		//Act
		testProduct = new Product("testProdNo", "testProdDesc", testSize, testIdProduct);
		
		//Assert
		assertTrue("Product constructor did not create product object", testProduct instanceof Product);
	}

	@Test
	public void Calling_getProdNo_ShouldReturn_prodNoMatchingAddedProdNo() {
		//Arrange
		int testIdSize = 1;
		Size testSize = new Size("testSize", testIdSize);
		int testIdProduct = 4444;
		String testProdNo = "1234prodNo";
		Product testProduct;
		testProduct = new Product(testProdNo, "testProdDesc", testSize, testIdProduct);
		String returnedProductNo = null;
		
		//Act
		returnedProductNo = testProduct.getProdNo();
				
		//Assert
		assertTrue("Returned prodNo doesn't match", returnedProductNo.equals(testProdNo));
	}

	@Test
	public void Calling_getProductDescription_ShouldReturn_productDescriptionMatchingProductDescriptionAdded() {
		//Arrange
		int testIdSize = 1;
		Size testSize = new Size("testSize", testIdSize);
		int testIdProduct = 4444;
		String testProdDesc = "TestDescription";
		Product testProduct;
		testProduct = new Product("testProdNo", testProdDesc, testSize, testIdProduct);
		String returnedProductDesc = null;
		
		//Act
		returnedProductDesc = testProduct.getProductDescription();
				
		//Assert
		assertTrue("Returned prodDesc doesn't match", returnedProductDesc.equals(testProdDesc));
	}

	@Test
	public void Calling_getSize_ShouldReturn_sizeMatchingSizeAdded() {
		//Arrange
		int testIdSize = 1;
		String testSizeDescription = "TestSizeDescription";
		Size testSize = new Size(testSizeDescription, testIdSize);
		int testIdProduct = 4444;
		Product testProduct;
		testProduct = new Product("testProdNo", "testProdDesc", testSize, testIdProduct);
		Size returnedProductSize = null;
		
		//Act
		returnedProductSize = testProduct.getSize();
				
		//Assert
		assertTrue("Returned prodSize doesn't match", returnedProductSize.equals(testSize));
	}

	@Test
	public void Calling_getIdProduct_ShouldReturn_idProductMatchingSizeAdded() {
		//Arrange
		int testIdSize = 1;
		Size testSize = new Size("testSizeDescription", testIdSize);
		int testIdProduct = 4444;
		Product testProduct;
		testProduct = new Product("testProdNo", "testProdDesc", testSize, testIdProduct);
		int returnedIdProduct;
		
		//Act
		returnedIdProduct = testProduct.getIdProduct();
		
		//Assert
		assertEquals("Returned idProduct doesn't match", testIdProduct, returnedIdProduct);
	}

}
