package org.magnum.dataup.service;

import org.magnum.dataup.model.Video;
import org.magnum.dataup.repository.VideoRepository;
import org.springframework.stereotype.Service;

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
}
