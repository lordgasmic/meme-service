package com.lordgasmic.memeservice.controller;

import com.lordgasmic.memeservice.model.FacetsResponse;
import com.lordgasmic.memeservice.model.MemeRequestRequest;
import com.lordgasmic.memeservice.model.MemeResponse;
import com.lordgasmic.memeservice.service.MemeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class MemeController {

    private final MemeService service;

    @Autowired
    public MemeController(final MemeService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/memes")
    public List<MemeResponse> getAllMemes() {
        return service.getAllMemes();
    }

    @GetMapping("/api/v1/memes/tag/{tag}")
    public List<MemeResponse> getMemes(@PathVariable final String tag) throws IOException, SolrServerException {
        return service.getMemesByTag(tag);
    }

    @PostMapping("/api/v1/meme")
    public void addMeme(@RequestParam("file") final MultipartFile file) throws IOException {
        service.addMeme(file);
    }

    @PutMapping("/api/v1/meme/request")
    public void addMemeRequest(@RequestBody final MemeRequestRequest request) {
        service.addMemeRequest(request);
    }

    @PutMapping("/api/v1/index")
    public void updateIndex() throws IOException, InterruptedException, SolrServerException {
        service.updateIndex();
    }

    @GetMapping("/api/v1/memes/facets")
    public List<FacetsResponse> getFacets() {
        return service.getFacets();
    }
}
