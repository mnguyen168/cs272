package edu.orangecoastcollege.cs272.foodiefit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBModel {
	private String mDBName;
	private String mTableName;
	private String[] mFieldNames;
	private String[] mFieldTypes;
	private Connection mConnection;
	private Statement mStmt;
	
	public DBModel(String dbName, String tableName, String[] fieldNames, String[] fieldTypes) throws SQLException {
		super();
		mDBName = dbName;
		mTableName = tableName;
		mFieldNames = fieldNames;
		mFieldTypes = fieldTypes;
		if (mFieldNames == null || mFieldTypes == null || mFieldNames.length == 0 || mFieldNames.length != mFieldTypes.length)
			throw new SQLException("Database field names and types must exist and have the same number of elements.");
		mConnection = connectToDB();
		mStmt = mConnection.createStatement();
		mStmt.setQueryTimeout(30);;
		createTable();
	}
	
	private Connection connectToDB() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + mDBName);
		return connection;
	}
	
	private void createTable() throws SQLException {
		StringBuilder createSQL = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
		createSQL.append(mTableName).append("(");
		for (int i = 0; i < mFieldNames.length; i++)
			createSQL.append(mFieldNames[i]).append(" ").append(mFieldTypes[i]).append((i < mFieldNames.length - 1) ? "," : ")");
		mStmt.executeUpdate(createSQL.toString());
	}
	
	public ResultSet getAllRecords() throws SQLException {
		return mStmt.executeQuery("SELECT * FROM " + mTableName);
	}
	
	public ResultSet getRecord(String key) throws SQLException {
		String selectSQL = "SELECT * FROM " + mTableName + " WHERE " + mFieldNames[0] + " = " + key;
		return mStmt.executeQuery(selectSQL);
	}
	
	public int getRecordCount() throws SQLException {
		int count = 0;
		ResultSet rs = getAllRecords();
		while (rs.next())
			count++;
		return count;
	}
	
	public int createRecord(String[] fields, String[] values) throws SQLException {
		if (fields == null || values == null || fields.length == 0 || fields.length != values.length)
			return -1;
		
		StringBuilder insertSQL = new StringBuilder("INSERT INTO ");
		insertSQL.append(mTableName).append("(");
		for (int i = 0; i < fields.length; i++)
			insertSQL.append(fields[i]).append((i < fields.length - 1) ? "," : ") VALUES(");
		for (int i = 0; i < values.length; i++)
			insertSQL.append(convertToSQLText(fields[i], values[i])).append((i < values.length - 1) ? "," : ")");
		
		mStmt.executeUpdate(insertSQL.toString());
		return mStmt.getGeneratedKeys().getInt(1);
	}
	
	public boolean updateRecord(String key, String[] fields, String[] values) throws SQLException {
		if (fields == null || values == null || fields.length == 0 || fields.length != values.length)
			return false;
		StringBuilder updateSQL = new StringBuilder("UPDATE ");
		updateSQL.append(mTableName).append(" SET ");
		for (int i = 0; i < fields.length; i++)
			updateSQL.append(fields[i]).append("=").append(convertToSQLText(fields[i], values[i])).append((i < fields.length - 1) ? ", " : " ");
		
		updateSQL.append("WHERE ").append(mFieldNames[0]).append("=").append(key);
		mStmt.executeUpdate(updateSQL.toString());
		return true;
	}
	
	public void deleteAllRecords() throws SQLException {
		mStmt.executeUpdate("DELETE FROM " + mTableName);
	}
	
	public void deleteRecord(String key) throws SQLException {
		mStmt.executeUpdate("DELETE FROM " + mTableName + " WHERE " + mFieldNames[0] + " = " + key);
	}
	
	private String convertToSQLText(String field, String value) {
		for (int i = 0; i < mFieldNames.length; i++) {
			if (mFieldNames[i].equals(field)) {
				if (mFieldTypes[i].toUpperCase().startsWith("TEXT"))
					return "'" + value + "'";
				break;
			}
		}
		return value;
	}
}
