package org.teamone.onlinestorebuyreadreview.database.statement.creator;

import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
public class PrepareStatementCreatorWithScrolledResultSet implements PreparedStatementCreator {
    private final String sql;

    public PrepareStatementCreatorWithScrolledResultSet(String sql) {
        this.sql = sql;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        return connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }
}
