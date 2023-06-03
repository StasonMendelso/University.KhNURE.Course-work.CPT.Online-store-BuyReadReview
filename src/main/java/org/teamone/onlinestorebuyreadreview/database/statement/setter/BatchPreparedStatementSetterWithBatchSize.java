package org.teamone.onlinestorebuyreadreview.database.statement.setter;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

/**
 * @author Stanislav Hlova
 */
public abstract class BatchPreparedStatementSetterWithBatchSize implements BatchPreparedStatementSetter {
    private int batchSize = 0;

    public BatchPreparedStatementSetterWithBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    @Override
    public int getBatchSize() {
        return batchSize;
    }
}
