package transaction;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private static final Logger LOG = LoggerFactory.getLogger(TransactionManager.class);
    HikariDataSource dataSource;

    {
        HikariConfig config = new HikariConfig("src/main/java/transaction/hikari.properties");
        dataSource = new HikariDataSource(config);
    }

    public <E extends Object> E doTransaction(TransactionOperation<E> transactionOperation) {
        Connection con = null;
        E result = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            result = transactionOperation.doTransaction(con);
            con.commit();
        } catch (Exception e) {
            LOG.debug("Transaction rollback");
            LOG.debug("Error: " + e.getMessage());
            rollback(con);
        } finally {
            closeConnection(con);
        }
        return result;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DAOException("Cannot rollback", e);
        }
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("Cannot close connection", e);
        }
    }
}
