package com.musiva.artists.event;

import com.musiva.artists.vo.ArtistType;

import java.time.Instant;
import java.util.UUID;

public record ArtistCreatedEvent(Instant occurredOn, UUID aggregateId, String name, ArtistType artistType) implements ArtistEvent {

    public ArtistCreatedEvent(UUID artistId, String name, ArtistType artistType) {
        this(Instant.now(), artistId, name, artistType);
    }
}
