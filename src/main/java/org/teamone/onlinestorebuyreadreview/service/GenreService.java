package org.teamone.onlinestorebuyreadreview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.teamone.onlinestorebuyreadreview.database.entity.Genre;
import org.teamone.onlinestorebuyreadreview.database.repository.GenreRepository;
import org.teamone.onlinestorebuyreadreview.dto.genre.ReadGenreDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.genre.GenreReadMapper;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    private final GenreReadMapper genreReadMapper;
    public List<ReadGenreDto> getGenres() {
        return genreRepository.readAll()
                .stream()
                .map(genreReadMapper::map)
                .toList();
    }

    public List<Genre> getGenresByName(List<String> genreNames) {
        return genreRepository.readAllByName(genreNames);
    }

    public List<Genre> saveGenres(List<Genre> genresForAdding) {
        return genreRepository.create(genresForAdding);
    }
}
