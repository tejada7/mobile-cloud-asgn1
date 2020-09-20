package org.magnum.dataup.service;

import org.magnum.dataup.model.Video;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static org.magnum.dataup.common.Utils.getUrlBaseForLocalServer;

@Component
public class VideoEnricher {
    private static final AtomicLong currentId = new AtomicLong(0L);

    private final Map<Long, Video> videosById = new HashMap<>();

    public Video enrich(Video entity) {
        checkAndSetId(entity);
        setUrl(entity);
        videosById.put(entity.getId(), entity);
        return entity;
    }

    private void setUrl(Video entity) {
        entity.setDataUrl(getUrlBaseForLocalServer() + "/video/" + entity.getId() + "/data");
    }

    private void checkAndSetId(Video entity) {
        if (entity.getId() == 0) {
            entity.setId(currentId.incrementAndGet());
        }
    }

}
