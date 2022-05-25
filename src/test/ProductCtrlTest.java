package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.ProductCtrl;
import model.Product;
import model.Size;

public class ProductCtrlTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void Calling_getProductWithCorrectProdNoAndSize_ShouldReturn_MatchingProductObject() {
		//Arrange
		ProductCtrl prodCtrl = new ProductCtrl();
		
		String testSizeDesc = "Small";
		Size testSize = new Size(testSizeDesc, 1);
		String testProdNo = "115";
		
		Product returnedProduct;
		
		//Act
		returnedProduct = prodCtrl.getProduct(testProdNo, testSize);
		
		//Assert
		assertTrue("", returnedProduct.getProdNo().equals(testProdNo) && returnedProduct.getSize().getSizeDesc().equals(testSizeDesc));
	}
	
//	@Test (expected = DataAccessException.class)
//	public void Calling_getProductWithIncorrectProdNoAndSize_ShouldReturn_MatchingProductObject() {
//		//Arrange
//		ProductCtrl prodCtrl = new ProductCtrl();
//		
//		String testSizeDesc = "Small";
//		Size testSize = new Size(testSizeDesc, 1);
//		String testProdNo = "9797979";
//		
//		Product returnedProduct;
//		
//		//Act
//		returnedProduct = prodCtrl.getProduct(testProdNo, testSize);
//		
//		//Assert
//		//assertThrows(DataAccessException.class, );
//	}

}
