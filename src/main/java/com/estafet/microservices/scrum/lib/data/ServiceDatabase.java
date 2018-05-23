package com.estafet.microservices.scrum.lib.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.io.Resources;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias(value = "service")
public class ServiceDatabase {

	@XStreamAlias(value = "name")
	private String name;

	@XStreamAlias(value = "db-url-env")
	private String dbURLEnvVariable;

	@XStreamAlias(value = "db-user-env")
	private String dbUserEnvVariable;

	@XStreamAlias(value = "db-password-env")
	private String dbPasswordEnvVariable;
	
	@XStreamOmitField
	Connection connection;

	@XStreamOmitField
	Statement statement;

	public String getName() {
		return name;
	}

	public String getDbURL() {
		return System.getenv(dbURLEnvVariable);
	}

	public String getDbUser() {
		return System.getenv(dbUserEnvVariable);
	}

	public String getDbPassword() {
		return System.getenv(dbPasswordEnvVariable);
	}

	public void init() {
		System.out.println("Connecting " + name + "...");
//		System.out.println(dbURLEnvVariable + " - " + getDbURL());
//		System.out.println(dbUserEnvVariable + " - " + getDbUser());
//		System.out.println(dbPasswordEnvVariable + " - " + getDbPassword());
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(getDbURL(), getDbUser(), getDbPassword());
			statement = connection.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean exists(String table, String key, Integer value) {
		try {
			String sqlselect = "select " + key + " from " + table + " where " + key + " = " + value;
			System.out.println("Executing " + sqlselect + "...");
			return statement.executeQuery(sqlselect).next();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close();
		}
	}
	
	public void clean() {
		System.out.println("Cleaning " + name + "...");
		try {
			executeDDL("drop", statement);
			executeDDL("create", statement);
			System.out.println("Successfully cleaned " + name + ".");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close();
		}
	}
	
	public void close() {
		try {
			System.out.println("Disconnected " + name + ".");
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void executeDDL(String prefix, Statement statement) throws SQLException {
		for (String stmt : getStatements(prefix + "-" + name + "-db.ddl")) {
			statement.executeUpdate(stmt.replaceAll("\\;", ""));
		}
	}

	private List<String> getStatements(String filename) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(Resources.getResource(filename).openStream()));
			return reader.lines().collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

}
