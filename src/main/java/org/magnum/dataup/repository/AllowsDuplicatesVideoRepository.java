package org.magnum.dataup.repository;

import org.magnum.dataup.model.Video;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class AllowsDuplicatesVideoRepository implements VideoRepository {

    // Lists allow duplicate objects that are .equals() to
    // each other
    //
    // Assume a lot more reads than writes
    private final List<Video> videoList = new CopyOnWriteArrayList<>();

    @Override
    public boolean addVideo(Video v) {
        return videoList.add(v);
    }

    @Override
    public Collection<Video> getVideos() {
        return videoList;
    }

    // Search the list of videos for ones with
    // matching titles.
    @Override
    public Collection<Video> findByTitle(String title) {
        Set<Video> matches = new HashSet<>();
        for (Video video : videoList) {
            if (video.getTitle().equals(title)) {
                matches.add(video);
            }
        }
        return matches;
    }

    @Override
    public Optional<Video> findById(long id) {
        return videoList.stream()
                .filter(video -> id == video.getId())
                .findFirst();
    }

}