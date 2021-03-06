package com.musiva.playlists.persistence.handler;

import com.musiva.playlists.persistence.PlaylistEntity;
import com.musiva.playlists.event.CollaboratorAddedEvent;
import com.musiva.playlists.persistence.JpaPlaylistRepository;
import domain.events.DomainEventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class CollaboratorAddedHandler implements DomainEventHandler<CollaboratorAddedEvent> {

    private final JpaPlaylistRepository jpaPlaylistRepository;

    public CollaboratorAddedHandler(JpaPlaylistRepository jpaPlaylistRepository) {
        this.jpaPlaylistRepository = jpaPlaylistRepository;
    }

    @Override
    public void handle(CollaboratorAddedEvent collaboratorAddedEvent) {
        final Optional<PlaylistEntity> playlist = jpaPlaylistRepository.findById(collaboratorAddedEvent.aggregateId());

        playlist.ifPresent(found -> {
            final Set<UUID> collaborators = found.getCollaborators();
            collaborators.add(collaboratorAddedEvent.collaborator().id());
            jpaPlaylistRepository.saveAndFlush(found);
        });
    }
}
