package org.teamone.onlinestorebuyreadreview.util.mapper.genre;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.Genre;
import org.teamone.onlinestorebuyreadreview.dto.genre.ReadGenreDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

/**
 * @author Stanislav Hlova
 */
@Component
public class GenreReadMapper implements Mapper<Genre, ReadGenreDto> {
    @Override
    public ReadGenreDto map(Genre genre) {
        return ReadGenreDto.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
