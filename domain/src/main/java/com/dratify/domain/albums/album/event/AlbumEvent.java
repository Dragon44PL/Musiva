package com.dratify.domain.albums.album.event;

import domain.events.DomainEvent;
import java.util.UUID;

public interface AlbumEvent extends DomainEvent<UUID> { }