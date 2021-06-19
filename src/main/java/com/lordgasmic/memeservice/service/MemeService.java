package com.lordgasmic.memeservice.service;

import com.google.gson.Gson;
import com.lordgasmic.memeservice.entity.MemeEntity;
import com.lordgasmic.memeservice.entity.PathEntity;
import com.lordgasmic.memeservice.entity.RequestEntity;
import com.lordgasmic.memeservice.entity.TagEntity;
import com.lordgasmic.memeservice.model.MemeRequestRequest;
import com.lordgasmic.memeservice.model.MemeResponse;
import com.lordgasmic.memeservice.model.solr.*;
import com.lordgasmic.memeservice.repository.MemeRepository;
import com.lordgasmic.memeservice.repository.PathRepository;
import com.lordgasmic.memeservice.repository.RequestRepository;
import com.lordgasmic.memeservice.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Service
@Slf4j
public class MemeService {

    @Autowired
    private MemeRepository memeRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PathRepository pathRepository;

    @Autowired
    private RequestRepository requestRepository;

    private static final Gson gson = new Gson();

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

    public void addMeme(MultipartFile file) throws IOException {
        File f = new File("/vol/" + file.getOriginalFilename());
        file.transferTo(f);
    }

    public void addMemeRequest(MemeRequestRequest request) {
        log.info("request = " + request);
        RequestEntity entity = new RequestEntity();
        entity.setName(request.getName());
        requestRepository.save(entity);
    }

    public void updateIndex() throws IOException, InterruptedException {
        List<TagEntity> tags = tagRepository.findAll();
        List<Doc> docs = tags.stream().map(Doc::fromTagEntity).collect(toList());

        Update update = new Update();
        update.setDelete(new Delete());
        update.setAdd(docs);
        update.setCommit(new Commit());

        String body = gson.toJson(update);
        log.info("body " + body);
        HttpRequest request = HttpRequest.newBuilder()
                                         .POST(HttpRequest.BodyPublishers.ofString(body))
                                         .uri(URI.create("http://172.16.0.51:8983/solr/memes/update"))
                                         .header("Content-Type", "application/json")
                                         .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        log.info("post request");
        SolrResponse solrResponse = gson.fromJson(response.body(), SolrResponse.class);

        log.info("seriliaize");
        if (response.statusCode() != 200) {
            throw new RuntimeException(solrResponse.toString());
        }
    }

    private void getMemeAttributesAndAssociate(List<String> memeIds, List<MemeResponse> response) {
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
