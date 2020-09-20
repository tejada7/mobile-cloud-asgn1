package org.magnum.dataup.service;

import org.magnum.dataup.model.Video;
import org.magnum.dataup.model.VideoStatus;
import org.magnum.dataup.repository.VideoFileManager;
import org.magnum.dataup.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Optional;

@Service
public class VideoService {

    private final VideoEnricher videoEnricher;
    private final VideoRepository noDuplicatesVideoRepository;
    private final VideoFileManager videoFileManager;

    public VideoService(VideoEnricher videoEnricher, VideoRepository noDuplicatesVideoRepository, VideoFileManager videoFileManager) {
        this.videoEnricher = videoEnricher;
        this.noDuplicatesVideoRepository = noDuplicatesVideoRepository;
        this.videoFileManager = videoFileManager;
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

    public Optional<byte[]> getVideoData(long id) throws IOException {
        final Optional<Video> videoById = noDuplicatesVideoRepository.findById(id);
        if (videoById.isPresent() && videoFileManager.hasVideoData(videoById.get())) {
            return Optional.ofNullable(videoFileManager.getVideoData(videoById.get()));
        }
        return Optional.empty();
    }

    public Optional<VideoStatus> addVideoData(long id, InputStream data) throws IOException {
        final Optional<Video> videoById = noDuplicatesVideoRepository.findById(id);
        if (videoById.isPresent()) {
            videoFileManager.saveVideoData(videoById.get(), data);
            return Optional.of(new VideoStatus(VideoStatus.VideoState.READY));
        }
        return Optional.empty();
    }
}
