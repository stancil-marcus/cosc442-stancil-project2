package edu.towson.cis.cosc442.project2.vendingmachine;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VendingMachineTest {

	static VendingMachine machine;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		machine = new VendingMachine();
	}

	@After
	public void tearDown() throws Exception {
		machine = null;
	}
	
	/**
	 * This method tests the getItem() method by creating an item, adding it to the machine in slot A and seeing if the item we
	 * just added to slot A is the same item we created.
	 */
	@Test
	public void testAddItem() {
		VendingMachineItem item = new VendingMachineItem("bisket", 2.0);
		machine.addItem(item, "A");
		assertSame(item, machine.getItem("A"));
	}
	
	/**
	 * This method tests the getItem() method by seeing if the prerequisite that an item must be added to a null
	 * slot is acknowledged. This method should fail.
	 */
	@Test 
	public void testAddItemFail()
	{
		VendingMachineItem item = new VendingMachineItem("cookie", 2.0);
		machine.addItem(item, "A");
		machine.addItem(item, "A");
	}
	
	/**
	 * This method tests the removeItem() method to see if it successfully removes an item from the vending machine.
	 */
	@Test
	public void testRemoveItem()
	{
		VendingMachineItem item = new VendingMachineItem("cookie", 2.0);
		machine.addItem(item, "A");
		machine.removeItem("A");
	}
	
	/**
	 * This method tests the removeItem() method to see if it fails when trying to remove an item from a null slot.
	 * This test should fail.
	 */
	@Test
	public void testRemoveItemFail()
	{
		machine.removeItem("A");
	}
	
	/**
	 * This method test the insertMoney() method. This method tests if the user can successfully enter money into
	 * the machine and uses the assertEquals method to see if our balance is the same amount of money we entered.
	 */
	@Test
	public void testInsertMoney()
	{
		machine.insertMoney(10.00);
		assertEquals(10.00, machine.getBalance(), 0.1);
	}
	
	/**
	 * This method tests to see if the insertMoney() method fails when you try to allocate a negative value to the machine.
	 * This test should fail.
	 */
	@Test
	public void testInsertMoneyFail()
	{
		machine.insertMoney(-10.00);
	}
	
	/**
	 * This method tests the getBalance() method by inserting money into the machine and then using getBalance() to see if the money
	 * the user should have in the machine is equal to their balance.
	 */
	@Test
	public void testGetBalance()
	{
		machine.insertMoney(10.00);
		assertEquals(10.00, machine.getBalance(),0.1);
	}
	
	/**
	 * This method tests the makePurchase() method by inserting money into the vending machine and using makePurchase method to see if
	 * the user can successfully make a purchase. If the makePurchase() method returns true, then the test was
	 * successful.
	 */
	@Test
	public void testMakePurchase()
	{
		machine.insertMoney(10.00);
		VendingMachineItem item = new VendingMachineItem("cookie", 2.0);
		machine.addItem(item, "A");
		assertTrue(machine.makePurchase("A"));
	}
	
	/**
	 * This method tests to see if makePurchase() fails when the user has insufficient funds. This test shouldn't
	 * fail.
	 */
	@Test
	public void testMakePurchaseFailLackOfFunds()
	{
		machine.insertMoney(1.00);
		VendingMachineItem item = new VendingMachineItem("cookie", 2.00);
		machine.addItem(item, "A");
		assertFalse(machine.makePurchase("A"));
	}
	
	/**
	 * This method tests to see it makePurchase() fails when the user inputs a wrong code. This test shouldn't
	 * fail.
	 */
	@Test 
	public void testMakePurchaseFailIncorrectCode()
	{
		machine.insertMoney(2.00);
		VendingMachineItem item = new VendingMachineItem("cookie", 2.00);
		machine.addItem(item, "A");
		assertFalse(machine.makePurchase("B"));
	}
	
	/**
	 * This method tests to see if the machine returns the correct change after the user makes a purchase.
	 */
	@Test
	public void testReturnChange()
	{
		machine.insertMoney(10.00);
		VendingMachineItem item = new VendingMachineItem("cookie", 2.0);
		machine.addItem(item, "A");
		assertTrue(machine.makePurchase("A"));
		assertEquals(8, machine.getBalance(),0.1);
	}

}
