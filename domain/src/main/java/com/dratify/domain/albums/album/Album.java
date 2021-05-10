package com.dratify.domain.albums.album;

import com.dratify.domain.albums.album.event.AlbumCreatedEvent;
import com.dratify.domain.albums.album.event.AlbumEvent;
import com.dratify.domain.albums.album.event.TrackAddedEvent;
import com.dratify.domain.albums.album.event.TrackRemovedEvent;
import com.dratify.domain.albums.album.vo.ArtistId;
import com.dratify.domain.albums.album.vo.TrackId;
import domain.AggregateRoot;

import java.util.*;

class Album extends AggregateRoot<UUID, AlbumEvent> {

    private final UUID id;
    private String name;
    private final Set<TrackId> tracks;
    private final ArtistId artist;

    static Album create(UUID id, String name, Set<TrackId> tracks, ArtistId artist) {
        final Album album = new Album(id, name, tracks, artist, new ArrayList<>());
        album.registerEvent(new AlbumCreatedEvent(album.id, album.name, album.tracks, album.artist));
        return album;
    }

    static Album restore(UUID id, String name, Set<TrackId> tracks, ArtistId artist) {
        return new Album(id, name, tracks, artist, new ArrayList<>());
    }

    private Album(UUID id, String name, Set<TrackId> tracks, ArtistId artist, List<AlbumEvent> events) {
        super(events);
        this.id = id;
        this.name = name;
        this.tracks = new HashSet<>(tracks);
        this.artist = artist;
    }

    void addTrack(TrackId track) {
        if(!hasTrack(track)) {
            processTrackAdding(track);
        }
    }

    private void processTrackAdding(TrackId track) {
        tracks.add(track);
        final TrackAddedEvent trackAddedEvent = new TrackAddedEvent(id, track);
        this.registerEvent(trackAddedEvent);
    }

    boolean hasTrack(TrackId track) {
        return tracks.contains(track);
    }

    void removeTrack(TrackId track) {
        if(hasTrack(track)) {
            processRemovingTrack(track);
        }
    }

    private void processRemovingTrack(TrackId track) {
        tracks.remove(track);
        final TrackRemovedEvent trackRemovedEvent = new TrackRemovedEvent(id, track);
        this.registerEvent(trackRemovedEvent);
    }
}
