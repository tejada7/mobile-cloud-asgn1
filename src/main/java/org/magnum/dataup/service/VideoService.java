package org.magnum.dataup.service;

import org.magnum.dataup.model.Video;
import org.magnum.dataup.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collection;
import java.util.Optional;

@Service
public class VideoService {

    private final VideoEnricher videoEnricher;
    private final VideoRepository noDuplicatesVideoRepository;

    public VideoService(VideoEnricher videoEnricher, VideoRepository noDuplicatesVideoRepository) {
        this.videoEnricher = videoEnricher;
        this.noDuplicatesVideoRepository = noDuplicatesVideoRepository;
    }

    public Video registerVideo(Video video) {
        final Video videoWithId = videoEnricher.enrich(video);
        noDuplicatesVideoRepository.addVideo(videoWithId);
        return videoWithId;
    }

    public Collection<Video> getAllVideos() {
        return noDuplicatesVideoRepository.getVideos();
    }

    public Optional<Video> getVideoById(long id) {
        return noDuplicatesVideoRepository.findById(id);
    }
}
