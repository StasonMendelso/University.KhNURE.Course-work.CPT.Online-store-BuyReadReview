package org.teamone.onlinestorebuyreadreview.database.mapper.file;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.File;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stanislav Hlova
 */
@Component
public class ReadFileRowMapper implements RowMapper<File> {
    @Override
    public File mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return File.builder()
                .id(resultSet.getLong("file_id"))
                .name(resultSet.getString("file_name"))
                .extension(resultSet.getString("file_extension"))
                .relativePath(resultSet.getString("file_relative_path"))
                .build();
    }
}
