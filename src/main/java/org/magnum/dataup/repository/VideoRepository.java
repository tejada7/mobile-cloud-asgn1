package org.magnum.dataup.repository;

import org.magnum.dataup.model.Video;

import java.util.Collection;
import java.util.Optional;

public interface VideoRepository {

    // Add a video
    boolean addVideo(Video v);

    // Get the videos that have been added so far
    Collection<Video> getVideos();

    // Find all videos with a matching title (e.g., Video.name)
    Collection<Video> findByTitle(String title);

    /**
     * Finds a video given its identifier.
     *
     * @param id the video's identifier
     * @return a {@link Video} object if present, otherwise {@link Optional#empty()}
     */
    Optional<Video> findById(long id);
}
