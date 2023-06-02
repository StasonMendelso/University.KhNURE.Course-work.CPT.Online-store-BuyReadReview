package org.teamone.onlinestorebuyreadreview.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.teamone.onlinestorebuyreadreview.service.FileService;

/**
 * @author Stanislav Hlova
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @GetMapping("/book/{file_id}")
    public ResponseEntity<byte[]> findBookFile(@PathVariable("file_id") Long id) {
        return fileService.findFileById(id)
                .map(content ->
                        ResponseEntity.ok()
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                                .contentLength(content.length)
                                .body(content))
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
