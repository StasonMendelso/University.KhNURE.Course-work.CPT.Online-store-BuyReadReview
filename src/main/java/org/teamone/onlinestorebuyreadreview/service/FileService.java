package org.teamone.onlinestorebuyreadreview.service;

import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.teamone.onlinestorebuyreadreview.config.properties.FileProperties;
import org.teamone.onlinestorebuyreadreview.database.repository.FileRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    private final FileProperties fileProperties;

    private final ServletContext context;
    public Optional<byte[]> findFileById(Long id) {
        return fileRepository.readFileRelativePathById(id)
                .flatMap(this::getFileBytes)
                .or(() -> fileRepository.readFileRelativePathById(0L)
                        .flatMap(this::getFileBytes));
    }

    @SneakyThrows
    private Optional<byte[]> getFileBytes(String imageRelativePath) {
        Path fullFilePath = fileProperties.getBucket().resolve(imageRelativePath);

        return Files.exists(fullFilePath) ? Optional.of(Files.readAllBytes(fullFilePath)) : Optional.empty();
    }
}
