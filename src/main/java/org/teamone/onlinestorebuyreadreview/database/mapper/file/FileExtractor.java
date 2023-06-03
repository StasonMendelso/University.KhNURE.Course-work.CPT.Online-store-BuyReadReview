package org.teamone.onlinestorebuyreadreview.database.mapper.file;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.File;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
@Component
@RequiredArgsConstructor
public class FileExtractor implements ResultSetExtractor<File> {
    private final FileRowMapper fileRowMapper;

    @Override
    public File extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        if (!resultSet.next()) {
            return null;
        }
        return fileRowMapper.mapRow(resultSet, 1);
    }
}
