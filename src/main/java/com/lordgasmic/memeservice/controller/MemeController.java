package com.lordgasmic.memeservice.controller;

import com.lordgasmic.memeservice.model.MemeRequestRequest;
import com.lordgasmic.memeservice.model.MemeResponse;
import com.lordgasmic.memeservice.service.MemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class MemeController {

    @Autowired
    private MemeService service;

    @GetMapping("/api/v1/memes")
    public List<MemeResponse> getAllMemes() {
        return service.getAllMemes();
    }

    @GetMapping("/api/v1/memes/tag/{tag}")
    public List<MemeResponse> getMemes(@PathVariable String tag) {
        return service.getMemesByTag(tag);
    }

    @PostMapping("/api/v1/meme")
    public void addMeme(@RequestParam("file") MultipartFile file) throws IOException {
        service.addMeme(file);
    }

    @PutMapping("/api/v1/meme/request")
    public void addMemeRequest(@RequestBody MemeRequestRequest request) {
        service.addMemeRequest(request);
    }

    @PutMapping("/api/v1/index")
    public void updateIndex() throws IOException, InterruptedException {
        service.updateIndex();
    }
}
