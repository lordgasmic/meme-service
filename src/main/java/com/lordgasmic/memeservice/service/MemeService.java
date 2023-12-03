package com.lordgasmic.memeservice.service;

import com.google.gson.Gson;
import com.lordgasmic.memeservice.entity.MemeEntity;
import com.lordgasmic.memeservice.entity.PathEntity;
import com.lordgasmic.memeservice.entity.RequestEntity;
import com.lordgasmic.memeservice.entity.TagEntity;
import com.lordgasmic.memeservice.model.MemeRequestRequest;
import com.lordgasmic.memeservice.model.MemeResponse;
import com.lordgasmic.memeservice.model.solr.Doc;
import com.lordgasmic.memeservice.repository.MemeRepository;
import com.lordgasmic.memeservice.repository.PathRepository;
import com.lordgasmic.memeservice.repository.RequestRepository;
import com.lordgasmic.memeservice.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.MapSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
@Slf4j
public class MemeService {

    private final MemeRepository memeRepository;

    private final TagRepository tagRepository;

    private final PathRepository pathRepository;

    private final RequestRepository requestRepository;

    private final SolrClient solrClient;

    @Autowired
    public MemeService(final MemeRepository memeRepository,
                       final TagRepository tagRepository,
                       final PathRepository pathRepository,
                       final RequestRepository requestRepository) {
        this.memeRepository = memeRepository;
        this.tagRepository = tagRepository;
        this.pathRepository = pathRepository;
        this.requestRepository = requestRepository;

        solrClient = new Http2SolrClient.Builder("http://solr:8983/solr/memes").build();
    }

    private static final Gson gson = new Gson();

    public List<MemeResponse> getAllMemes() {
        final List<MemeEntity> memes = memeRepository.findAll();
        final List<String> memeIds = memes.stream().map(MemeEntity::getId).collect(toList());

        final List<MemeResponse> response = new ArrayList<>();

        getMemeAttributesAndAssociate(memeIds, response);

        return response;
    }

    public List<MemeResponse> getMemesByTag(final String tag) throws IOException, SolrServerException {
        final Map<String, String> queryParamMap = new HashMap<>();
        queryParamMap.put("q", "tag:" + tag);
        final QueryResponse solrResponse = solrClient.query(new MapSolrParams(queryParamMap));
        final List<String> tagIds = solrResponse.getResults().stream().map(d -> (String) d.get("id")).collect(toList());
        final List<MemeEntity> memes = memeRepository.findByIdIn(tagIds);
        final List<String> memeIds = memes.stream().map(MemeEntity::getId).collect(toList());

        final List<MemeResponse> response = new ArrayList<>();

        getMemeAttributesAndAssociate(memeIds, response);

        return response;
    }

    public void addMeme(final MultipartFile file) throws IOException {
        final File f = new File("/vol/" + file.getOriginalFilename());
        file.transferTo(f);
    }

    public void addMemeRequest(final MemeRequestRequest request) {
        log.info("request = " + request);
        final RequestEntity entity = new RequestEntity();
        entity.setName(request.getName());
        requestRepository.save(entity);
    }

    public void updateIndex() throws IOException, SolrServerException {
        final List<TagEntity> tags = tagRepository.findAll();

        final Map<String, List<String>> tagMap = new HashMap<>();
        for (final TagEntity tag : tags) {
            tagMap.computeIfAbsent(tag.getPk().getId(), k -> new ArrayList<>());

            tagMap.get(tag.getPk().getId()).add(tag.getTag());
        }

        final List<Doc> docs = new ArrayList<>();
        for (final String key : tagMap.keySet()) {
            docs.add(new Doc(key, tagMap.get(key)));
        }

        solrClient.deleteByQuery("*:*");

        solrClient.addBeans(docs);

        solrClient.commit();
    }

    public List<TagEntity> getFacets() {
        return tagRepository.countDistinctTag();
    }

    private void getMemeAttributesAndAssociate(final List<String> memeIds, final List<MemeResponse> response) {
        final List<TagEntity> tags = tagRepository.findByPkIdIn(memeIds);
        final List<PathEntity> paths = pathRepository.findAllByIdIn(memeIds);
        final Map<String, List<TagEntity>> tagMap = tags.stream().collect(groupingBy(tag -> tag.getPk().getId()));
        final Map<String, String> pathMap = paths.stream().collect(toMap(PathEntity::getId, PathEntity::getPath));
        final Map<String, String> thumbnailPathMap = paths.stream().collect(toMap(PathEntity::getId, PathEntity::getThumbnailPath));

        for (final String s : memeIds) {
            final MemeResponse meme = new MemeResponse();
            meme.setName(s);
            meme.setTags(tagMap.get(s).stream().map(TagEntity::getTag).collect(toList()));
            meme.setUrl(pathMap.get(s));
            meme.setThumbnailUrl(thumbnailPathMap.get(s));

            response.add(meme);
        }
    }
}
