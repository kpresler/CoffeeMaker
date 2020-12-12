package edu.ncsu.csc.CoffeeMaker.common;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.ncsu.csc.CoffeeMaker.TestConfig;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = TestConfig.class)
public class RebuildDB {

	static final private String DB_NAME = "springtrans";

	@Test
	public void testRebuildDB() throws Exception {

		showTables();
	}

	@Autowired
	protected DataSource dataSource;

	public void showTables() throws Exception {
		DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
		ResultSet tables = metaData.getTables(DB_NAME, null, null, new String[] { "TABLE" });

		Connection conn = dataSource.getConnection();
		try (Statement st = conn.createStatement();) {

			st.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
			
			while (tables.next()) {
				String tableName = tables.getString("TABLE_NAME");
				st.executeUpdate("TRUNCATE TABLE " + tableName);
			}
			
			st.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");

		}
		catch (Exception e) {
			System.err.println("Something bad appears to have happened while preparing environment");
			e.printStackTrace(System.err);
			Assert.fail();
		}
	}

}
