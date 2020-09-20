package org.magnum.dataup.repository;

import org.magnum.dataup.model.Video;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class NoDuplicatesVideoRepository implements VideoRepository {

    // Sets only store one instance of each object and will not
    // store a duplicate instance if two objects are .equals()
    // to each other.
    //
    private final Set<Video> videoSet = Collections.newSetFromMap(new ConcurrentHashMap<>());

    @Override
    public boolean addVideo(Video v) {
        return videoSet.add(v);
    }

    @Override
    public Collection<Video> getVideos() {
        return videoSet;
    }

    // Search the list of videos for ones with
    // matching titles.
    @Override
    public Collection<Video> findByTitle(String title) {
        Set<Video> matches = new HashSet<>();
        for (Video video : videoSet) {
            if (video.getTitle().equals(title)) {
                matches.add(video);
            }
        }
        return matches;
    }

}
