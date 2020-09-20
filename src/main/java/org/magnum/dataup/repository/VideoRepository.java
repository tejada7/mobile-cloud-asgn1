package org.magnum.dataup.repository;

import org.magnum.dataup.model.Video;

import java.util.Collection;

public interface VideoRepository {

    // Add a video
    public boolean addVideo(Video v);

    // Get the videos that have been added so far
    public Collection<Video> getVideos();

    // Find all videos with a matching title (e.g., Video.name)
    public Collection<Video> findByTitle(String title);

}
