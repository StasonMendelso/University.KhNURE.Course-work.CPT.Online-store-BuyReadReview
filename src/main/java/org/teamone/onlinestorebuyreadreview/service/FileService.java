package org.teamone.onlinestorebuyreadreview.service;

import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.teamone.onlinestorebuyreadreview.config.properties.FileProperties;
import org.teamone.onlinestorebuyreadreview.database.entity.Book;
import org.teamone.onlinestorebuyreadreview.database.entity.File;
import org.teamone.onlinestorebuyreadreview.database.repository.FileRepository;
import org.teamone.onlinestorebuyreadreview.util.mapper.file.FileMultiPartMapper;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileService {
    private final FileRepository fileRepository;

    private final FileProperties fileProperties;
    private final FileMultiPartMapper fileMultiPartMapper;

    private final static String DELIMITER = ".";

    public Optional<byte[]> findFileById(Long id) {
        return fileRepository.readFileRelativePathById(id)
                .flatMap(this::getFileBytes)
                .or(() -> fileRepository.readFileRelativePathById(0L)
                        .flatMap(this::getFileBytes));
    }

    @SneakyThrows
    private Optional<byte[]> getFileBytes(String fileRelativePath) {
        Path fullFilePath = fileProperties.getBucket().resolve(fileRelativePath);

        return Files.exists(fullFilePath) ? Optional.of(Files.readAllBytes(fullFilePath)) : Optional.empty();
    }

    @Transactional
    @SneakyThrows
    public void uploadBookFile(Book book, MultipartFile multipartFile) {
        File file = fileMultiPartMapper.unmap(multipartFile);

        file.setName(generateNameForBookFile(book).concat(DELIMITER).concat(file.getExtension()));
        file.setRelativePath(generateRelativePathInBookDirectory(file.getName()));
        Optional<File> fileOptional = fileRepository.create(file);
        if (fileOptional.isPresent()){
            fileRepository.bindFileWithBook(fileOptional.get(),book);
            upload(fileOptional.get().getRelativePath(),multipartFile.getInputStream());
        }
    }

    private String generateRelativePathInBookDirectory(String fileName) {
        return String.format("%s/%s",fileProperties.getBookDirectoryBucketName(),fileName);
    }

    private String generateNameForBookFile(Book book) {
        List<File> files = fileRepository.readAllByBookId(book.getId());
        List<String> fileNames = files
                .stream()
                .map(file -> file.getName().split("\\.")[0])
                .toList();
        int number = 1;
        String name;
        do {
            name = String.format("%d_%d", book.getId(), number);
            number++;
        } while (fileNames.contains(name));
        return name;
    }

    @SneakyThrows
    private void upload(String fileRelativePath, InputStream content) {
        Path fullFilePath = fileProperties.getBucket().resolve(fileRelativePath);
        try (content) {
            Files.write(fullFilePath, content.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING); // not for large files
        }
    }
}
