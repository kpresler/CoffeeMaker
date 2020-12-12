package edu.ncsu.csc.CoffeeMaker.cucumber;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc
public class CucumberTestContextConfiguration {

    static final private String DB_NAME = "springtrans";

    @Autowired
    protected DataSource        dataSource;

    @Transactional
    private void resetTables () {
        try ( Connection conn = dataSource.getConnection(); ) {

            final DatabaseMetaData metaData = conn.getMetaData();
            final ResultSet tables = metaData.getTables( DB_NAME, null, null, new String[] { "TABLE" } );

            try ( Statement st = conn.createStatement(); ) {

                st.executeUpdate( "SET FOREIGN_KEY_CHECKS = 0" );

                while ( tables.next() ) {
                    final String tableName = tables.getString( "TABLE_NAME" );

                    if ( "hibernate_sequence".equals( tableName ) ) {
                        continue;
                    }

                    st.executeUpdate( "TRUNCATE TABLE " + tableName );
                }

                st.executeUpdate( "SET FOREIGN_KEY_CHECKS = 1" );

            }
        }
        catch ( final Exception e ) {
            System.err.println( "Something bad appears to have happened while preparing environment " + e.getClass() );
            // e.printStackTrace( System.err );
            Assert.fail();
        }
    }

    @Before
    public void beforeTests () {
        resetTables();
    }

}
