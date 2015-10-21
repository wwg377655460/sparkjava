package config;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by wsdevotion on 15/10/18.
 */
public final class jdbcConnection {

        private static DataSource myDataSource = null;

        private jdbcConnection() {
        }

        static {
            try {
                Properties prop = new Properties();
                // prop.setProperty("driverClassName", "com.mysql.jdbc.Driver");
                prop.setProperty("driverClassName", "com.mysql.jdbc.Driver");
                prop.setProperty("url","jdbc:mysql://localhost:3307/save?useUnicode=true&amp;characterEncoding=utf-8");
                prop.setProperty("username","root");
                prop.setProperty("password","root");

                myDataSource = BasicDataSourceFactory.createDataSource(prop);
            } catch (Exception e) {
                throw new ExceptionInInitializerError(e);
            }
        }

    public static DataSource getDataSource() {
        return myDataSource;
        }

        public static Connection getConnection() throws SQLException {
            return myDataSource.getConnection();
        }

        public static void free(ResultSet rs, Statement st, Connection conn) {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (st != null)
                        st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null)
                        try {
                            conn.close();
                            // myDataSource.free(conn);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            }
    }

}
