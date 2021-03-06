package com.musiva.albums.album.event;

import com.musiva.albums.album.vo.TrackId;

import java.time.Instant;
import java.util.UUID;

public record TrackRemovedEvent(Instant occurredOn, UUID aggregateId, TrackId track) implements AlbumEvent {

    public TrackRemovedEvent(UUID albumId, TrackId track) {
        this(Instant.now(), albumId, track);
    }
}
