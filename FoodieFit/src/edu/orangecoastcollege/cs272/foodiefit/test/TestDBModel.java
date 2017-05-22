package edu.orangecoastcollege.cs272.foodiefit.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.orangecoastcollege.cs272.foodiefit.model.DBModel;

/**
 * The <code>TestDBModel</code> tests the methods within the <code>DBModel</code> class.
 * 
 * @author Michael Nguyen
 * @version 1.0
 *
 */
public class TestDBModel {
	
	private static final String DB_NAME = "cs272_test.db";
	private static final String TABLE_NAME = "foodie_fit";
	private static final String[] USER_FIELD_NAMES = { "id", "username", "name", "age", "height", "weight", "password" };
	private static final String[] USER_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "TEXT", "INTEGER", "INTEGER", "INTEGER", "TEXT" };
	private static final String DATA_FILE = "food_lite.csv";
	private static DBModel db;
	private String[] values;
	
	/**
	 * The <code>setUpBeforeClass</code> sets up the database before the class. The set up
	 * before class defines variables, resources, etc. Only executes once before all testing
	 * begins. Anything you need to setup before testing will be done here.
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		db = new DBModel(DB_NAME, TABLE_NAME, USER_FIELD_NAMES, USER_FIELD_TYPES);
	}
	
	/**
	 * The <code>tearDownAfterClass</code> closes the database after the class. It also cleans
	 * up any open resources. Only executes once at the end of all testing.
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
	/**
	 * The <code>setUp</code> sets up the execution before each individual test.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		values = new String[] { "1", "Test User", "Test Name", "50", "70", "150", "Password!1" };
	}
	
	/**
	 * The <code>tearDown</code> removes all records from the database. This is executed
	 * after each individual test.
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		db.deleteAllRecords();
	}
	
	/**
	 * The <code>testGetAllRecords</code> tests the <code>getAllRecords</code> method
	 * from the <code>DBModel</code> class.
	 */
	@Test
	public void testGetAllRecords() {
		try {
			db.getAllRecords();
		} catch (SQLException e) {
			fail("Getting all records on empty database should not generate SQLException.");
		}
	}
	
	/**
	 * The <code>testGetRecord</code> tests the <code>getRecord</code> method from the
	 * <code>DBModel</code> class.
	 */
	@Test
	public void testGetRecord() {
		try {
			db.getRecord(values[0]);
		} catch (SQLException e) {
			fail("Getting recordon empty database should not generate SQLException.");
		}
		
		try {
			db.createRecord(USER_FIELD_NAMES, values);
			db.getRecord(values[0]);
		} catch (SQLException e) {
			fail("Getting record on non-empty database should not generate SQLException.");
		}
	}
	
	/**
	 * THe <code>testGetRecordCount</code> tests the <code>getRecordCount</code> method
	 * from the <code>DBModel</code> class.
	 */
	@Test
	public void testGetRecordCount() {
		try {
			db.getRecordCount();
		} catch (SQLException e) {
			fail("Getting record count should not generate an SQLException.");
		}
	}
	
	/**
	 * The <code>testCreateRecord</code> tests the <code>createRecord</code> method from
	 * the <code>DBModel</code> class.
	 */
	@Test
	public void testCreateRecord() {
		try {
			assertEquals("Testing the count of records.", 1, db.createRecord(USER_FIELD_NAMES, values));
			assertEquals("Testing the count of records.", 1, db.getRecordCount());
		} catch (SQLException e) {
			fail("Creation of records should not generate an SQLException.");
		}
		
		try {
			db.createRecord(USER_FIELD_NAMES, values);
			fail("Creating a record with duplicate ids should generate an SQLException.");
		} catch (SQLException e) {
			
		}
	}
	
	/**
	 * The <code>testUpdateRecord</code> tests the <code>updateRecord</code> method from
	 * the <code>DBModel</code> class.
	 */
	@Test
	public void testUpdateRecord() {
		try {
			db.updateRecord(values[0],  USER_FIELD_NAMES, values);
		} catch (SQLException e) {
			fail("Updating a record should not generate an SQLException.");
		}
	}
	
	/**
	 * The <code>testDeleteAllRecords</code> tests the <code>deleteAllRecords</code> method
	 * from the <code>DBModel</code> class.
	 */
	@Test
	public void testDeleteAllRecords() {
		try {
			db.createRecord(Arrays.copyOfRange(USER_FIELD_NAMES,  1, USER_FIELD_NAMES.length), Arrays.copyOfRange(values, 1, values.length));
			assertTrue("Before deletion, count should be positive", db.getRecordCount() > 0);
			db.deleteAllRecords();
			assertEquals("Count after deletion should be 0.", 0, db.getRecordCount());
		} catch (SQLException e) {
			fail("Deletion should not generate an SQLException.");
		}
	}
	
	/**
	 * The <code>testDeleteRecord</code> tests the <code>deleteRecord</code> method from the
	 * <code>DBModel</code> class.
	 */
	@Test
	public void testDeleteRecord() {
		try {
			db.createRecord(Arrays.copyOfRange(USER_FIELD_NAMES,  1,  USER_FIELD_NAMES.length), Arrays.copyOfRange(values,  1,  values.length));
			assertTrue("Before deletion, count should be positive", db.getRecordCount() > 0);
			db.deleteRecord(values[0]);
			assertEquals("Count after deletion should be 0.", 0, db.getRecordCount());
		} catch (SQLException e) {
			fail("Deletion should not generate an SQLException.");
		}
	}

}
