package org.teamone.onlinestorebuyreadreview.util.mapper.file;

import org.springframework.stereotype.Component;
import org.teamone.onlinestorebuyreadreview.database.entity.File;
import org.teamone.onlinestorebuyreadreview.dto.file.ReadFileDto;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

/**
 * @author Stanislav Hlova
 */
@Component
public class FileReadMapper implements Mapper<File, ReadFileDto> {
    @Override
    public ReadFileDto map(File file) {
        return ReadFileDto.builder()
                .id(file.getId())
                .extension(file.getExtension())
                .relativePath(file.getRelativePath())
                .name(file.getName())
                .build();
    }
}
