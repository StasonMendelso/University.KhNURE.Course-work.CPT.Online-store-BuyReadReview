package org.teamone.onlinestorebuyreadreview.util.mapper.file;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.teamone.onlinestorebuyreadreview.database.entity.File;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Component
public class FileMultiPartMapper implements Mapper<File, MultipartFile> {
    @Override
    public MultipartFile map(File object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public File unmap(MultipartFile multipartFile) {
        return File.builder()
                .extension(getExtensionByStringHandling(multipartFile.getOriginalFilename()).get())
                .build();
    }
    public Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}
