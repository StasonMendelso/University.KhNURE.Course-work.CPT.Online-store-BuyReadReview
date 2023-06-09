package org.teamone.onlinestorebuyreadreview.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Repository
@AllArgsConstructor
public class PracticeRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<MockDto> getAllData(){
        return jdbcTemplate.query("",(resultSet, rowNum) -> {

            return MockDto.builder()
                    .field1(resultSet.getString("column_name"))
                    .build();
        });
    }
}
