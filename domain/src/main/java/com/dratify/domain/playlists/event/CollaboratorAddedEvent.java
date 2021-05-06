package com.dratify.domain.playlists.event;

import com.dratify.domain.playlists.vo.UserId;
import java.time.Instant;
import java.util.UUID;

public record CollaboratorAddedEvent(Instant occurredOn, UUID aggregateId, UserId collaborator) implements PlaylistEvent {

    public CollaboratorAddedEvent(UUID playlistId, UserId collaborator) {
        this(Instant.now(), playlistId, collaborator);
    }
}
