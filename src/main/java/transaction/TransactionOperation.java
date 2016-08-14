package transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Vitalii_Bandura on 4/27/2015.
 */
public abstract class TransactionOperation<E extends Object> {
    public abstract E doTransaction(Connection connection) throws SQLException;
}
