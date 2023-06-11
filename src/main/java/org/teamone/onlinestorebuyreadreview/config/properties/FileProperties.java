package org.teamone.onlinestorebuyreadreview.config.properties;

import lombok.Data;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Stanislav Hlova
 */
@ConfigurationProperties(prefix = "app.file")
@Data
public class FileProperties{
    private final Path bucket;
    private final String bookDirectoryBucketName;

    public FileProperties(String bucket, @Value("${app.file.book-directory-bucket-name}") String bookDirectoryBucketName){
        this.bookDirectoryBucketName = bookDirectoryBucketName;

        if(bucket.startsWith("rel:")){
            bucket = bucket.substring(4);
        }
        Path pathToBucket = Path.of(bucket);
        if(bucket.isBlank() || bucket.equals("<your bucket name>") || !Files.isDirectory(pathToBucket)) {
            throw new InvalidPropertyException(FileProperties.class, "app.file.bucket", "You must enter a bucket! It must be a path of directory.");
        }

        this.bucket = pathToBucket;
    }
}
