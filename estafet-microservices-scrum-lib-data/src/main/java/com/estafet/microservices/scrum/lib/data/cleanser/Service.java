package com.estafet.microservices.scrum.lib.data.cleanser;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.common.io.Resources;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value = "service")
public class Service {

	@XStreamAlias(value = "name")
	private String name;

	@XStreamAlias(value = "db-url-env")
	private String dbURLEnvVariable;

	@XStreamAlias(value = "db-user-env")
	private String dbUserEnvVariable;

	@XStreamAlias(value = "db-password-env")
	private String dbPasswordEnvVariable;

	public String getName() {
		return name;
	}

	public String getDbURLEnvVariable() {
		return dbURLEnvVariable;
	}

	public String getDbUserEnvVariable() {
		return dbUserEnvVariable;
	}

	public String getDbPasswordEnvVariable() {
		return dbPasswordEnvVariable;
	}

	public void clean() {
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(System.getenv(dbURLEnvVariable), System.getenv(dbUserEnvVariable),
					System.getenv(dbPasswordEnvVariable));
			statement = connection.createStatement();
			executeDDL("drop", statement);
			executeDDL("create", statement);
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
			try {
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
	}

	private void executeDDL(String prefix, Statement statement) throws SQLException {
		for (String stmt : getStatements(prefix + "-" + name + "-db.ddl")) {
			System.out.println(stmt);
			statement.executeUpdate(stmt.replaceAll("\\;", ""));
		}
	}

	private List<String> getStatements(String filename) {
		try {
			return FileUtils.readLines(new File(Resources.getResource(filename).getPath()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
