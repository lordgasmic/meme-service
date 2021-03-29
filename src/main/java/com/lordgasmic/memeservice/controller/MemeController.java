package com.lordgasmic.memeservice.controller;

import com.lordgasmic.memeservice.model.CreateMemeRequest;
import com.lordgasmic.memeservice.model.MemeRequest;
import com.lordgasmic.memeservice.model.MemeRequestRequest;
import com.lordgasmic.memeservice.model.MemeResponse;
import com.lordgasmic.memeservice.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemeController {

    @Autowired
    private MemeService service;

    @GetMapping("/api/v1/memes")
    public List<MemeResponse> getAllMemes(){
        return service.getAllMemes();
    }

    @GetMapping("/api/v1/memes/tag/{tag}")
    public List<MemeResponse> getMemes(@PathVariable String tag){
        return service.getMemesByTag(tag);
    }

    @PutMapping("/api/v1/meme")
    public void addMeme(CreateMemeRequest request){
        service.addMeme(request);
    }

    @PutMapping("/api/v1/memes/request")
    public void addMemeRequest(MemeRequestRequest request){
        service.addMemeRequest(request);
    }
}
