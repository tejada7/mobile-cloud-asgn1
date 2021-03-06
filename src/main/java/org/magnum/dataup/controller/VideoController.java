/*
 *
 * Copyright 2014 Jules White
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.magnum.dataup.controller;

import org.apache.commons.io.IOUtils;
import org.magnum.dataup.model.Video;
import org.magnum.dataup.model.VideoStatus;
import org.magnum.dataup.service.VideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

/**
 * You will need to create one or more Spring controllers to fulfill the
 * requirements of the assignment. If you use this file, please rename it
 * to something other than "AnEmptyController"
 * <p>
 * <p>
 * ________  ________  ________  ________          ___       ___  ___  ________  ___  __
 * |\   ____\|\   __  \|\   __  \|\   ___ \        |\  \     |\  \|\  \|\   ____\|\  \|\  \
 * \ \  \___|\ \  \|\  \ \  \|\  \ \  \_|\ \       \ \  \    \ \  \\\  \ \  \___|\ \  \/  /|_
 * \ \  \  __\ \  \\\  \ \  \\\  \ \  \ \\ \       \ \  \    \ \  \\\  \ \  \    \ \   ___  \
 * \ \  \|\  \ \  \\\  \ \  \\\  \ \  \_\\ \       \ \  \____\ \  \\\  \ \  \____\ \  \\ \  \
 * \ \_______\ \_______\ \_______\ \_______\       \ \_______\ \_______\ \_______\ \__\\ \__\
 * \|_______|\|_______|\|_______|\|_______|        \|_______|\|_______|\|_______|\|__| \|__|
 */

@Controller
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @RequestMapping(path = "/video", method = RequestMethod.GET)
    public @ResponseBody Collection<Video> getVideoList() {
        return videoService.getAllVideos();
    }

    @RequestMapping(path = "/video", method = RequestMethod.POST)
    public @ResponseBody Video addVideo(@RequestBody Video video) {
        return videoService.registerVideo(video);
    }

    @RequestMapping(path = "/video/{id}/data", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<VideoStatus> setVideoData(@PathVariable("id") long id, /*TypedFile*/@RequestPart("data") MultipartFile videoData) throws IOException {
        return ResponseEntity.of(videoService.addVideoData(id, videoData.getInputStream()));
    }

    @RequestMapping(path = "/video/{id}/data", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<byte[]> getData(@PathVariable("id") long id) throws IOException {
        return ResponseEntity.of(videoService.getVideoData(id));
    }
}
