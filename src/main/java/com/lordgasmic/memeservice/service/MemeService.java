package com.lordgasmic.memeservice.service;

import com.lordgasmic.memeservice.model.CreateMemeRequest;
import com.lordgasmic.memeservice.model.MemeRequest;
import com.lordgasmic.memeservice.model.MemeRequestRequest;
import com.lordgasmic.memeservice.model.MemeResponse;
import com.lordgasmic.memeservice.repository.MemeRepository;
import com.lordgasmic.memeservice.repository.PathRepository;
import com.lordgasmic.memeservice.repository.RequestRepository;
import com.lordgasmic.memeservice.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemeService {

    @Autowired
    private MemeRepository memeRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PathRepository pathRepository;

    @Autowired
    private RequestRepository requestRepository;


    public List<MemeResponse> getAllMemes() {
        return null;
    }

    public List<MemeResponse> getMemesByTag(MemeRequest request) {
        return null;
    }

    public void addMeme(CreateMemeRequest request) {
        
    }

    public void addMemeRequest(MemeRequestRequest request) {

    }
}
