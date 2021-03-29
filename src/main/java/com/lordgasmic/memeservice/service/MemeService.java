package com.lordgasmic.memeservice.service;

import com.lordgasmic.memeservice.entity.MemeEntity;
import com.lordgasmic.memeservice.entity.PathEntity;
import com.lordgasmic.memeservice.entity.TagEntity;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

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
        List<MemeEntity> memes = memeRepository.findAll();
        List<String> memeIds = memes.stream().map(MemeEntity::getId).collect(toList());

        List<MemeResponse> response = new ArrayList<>();

        getMemeAttributesAndAssociate(memeIds, response);

        return response;
    }

    public List<MemeResponse> getMemesByTag(String tag) {
        List<TagEntity> tags = tagRepository.findByTag(tag);
        List<String> tagIds = tags.stream().map(t -> t.getPk().getId()).collect(toList());
        List<MemeEntity> memes = memeRepository.findByIdIn(tagIds);
        List<String> memeIds = memes.stream().map(MemeEntity::getId).collect(toList());

        List<MemeResponse> response = new ArrayList<>();

        getMemeAttributesAndAssociate(memeIds, response);

        return response;
    }

    public void addMeme(CreateMemeRequest request) {

    }

    public void addMemeRequest(MemeRequestRequest request) {

    }

    private void getMemeAttributesAndAssociate(List<String> memeIds,
                                               List<MemeResponse> response) {
        List<TagEntity> tags = tagRepository.findByPkIdIn(memeIds);
        List<PathEntity> paths = pathRepository.findAllByIdIn(memeIds);
        Map<String, List<TagEntity>> tagMap = tags.stream().collect(groupingBy(tag -> tag.getPk().getId()));
        Map<String, String> pathMap = paths.stream().collect(toMap(PathEntity::getId, PathEntity::getPath));

        for (String s : memeIds) {
            MemeResponse meme = new MemeResponse();
            meme.setName(s);
            meme.setTags(tagMap.get(s).stream().map(TagEntity::getTag).collect(toList()));
            meme.setUrl(pathMap.get(s));

            response.add(meme);
        }
    }
}
