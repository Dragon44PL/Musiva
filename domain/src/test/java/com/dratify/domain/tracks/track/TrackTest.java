package com.dratify.domain.tracks.track;

import com.dratify.domain.tracks.track.event.TrackCreatedEvent;
import com.dratify.domain.tracks.track.event.TrackEvent;
import com.dratify.domain.tracks.track.event.TrackListenedEvent;
import com.dratify.domain.tracks.track.vo.ListeningCounter;
import com.dratify.domain.tracks.track.vo.TrackDataPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TrackTest {

    private final UUID TRACK_ID = UUID.randomUUID();
    private final String EXAMPLE_TRACK_NAME = "Track";
    private final TrackDataPath EXAMPLE_TRACK_DATA_PATH = new TrackDataPath("path", "filename", "test");

    /*
        Track Events
     */

    Class<TrackCreatedEvent> TRACK_CREATED_EVENT = TrackCreatedEvent.class;
    Class<TrackListenedEvent> TRACK_LISTENED_EVENT = TrackListenedEvent.class;

    @Test
    @DisplayName("Track Should Create Properly And Generate TrackCreatedEvent")
    void trackShouldCreateProperlyAndGenerateEvent() {
        final Track track = Track.create(TRACK_ID, EXAMPLE_TRACK_NAME, EXAMPLE_TRACK_DATA_PATH);

        Optional<TrackEvent> trackEvent = track.findLatestEvent();
        assertTrue(trackEvent.isPresent());
        assertEquals(TRACK_CREATED_EVENT, trackEvent.get().getClass());

        final TrackCreatedEvent trackCreatedEvent = (TrackCreatedEvent) trackEvent.get();
        assertEquals(TRACK_ID, trackCreatedEvent.aggregateId());
        assertEquals(EXAMPLE_TRACK_NAME, trackCreatedEvent.name());
        assertEquals(EXAMPLE_TRACK_DATA_PATH, trackCreatedEvent.trackDataPath());
        assertEquals(0, trackCreatedEvent.listeningCounter().count());
    }

    @Test
    @DisplayName("Track Should Restore Properly And Not Generate Event")
    void trackShouldRestoreProperlyAndNotGenerateEvent() {
        final Track track = Track.restore(TRACK_ID, EXAMPLE_TRACK_NAME, EXAMPLE_TRACK_DATA_PATH, ListeningCounter.zero());

        Optional<TrackEvent> trackEvent = track.findLatestEvent();
        assertFalse(trackEvent.isPresent());
    }

    @Test
    @DisplayName("Track Should Be Added To TrackLibrary When Not Appear To Already Be")
    void trackShouldBeListenedAndGenerateEvent() {
        final Track track = Track.restore(TRACK_ID, EXAMPLE_TRACK_NAME, EXAMPLE_TRACK_DATA_PATH, ListeningCounter.zero());
        track.trackListened();

        Optional<TrackEvent> trackEvent = track.findLatestEvent();
        assertTrue(trackEvent.isPresent());
        assertEquals(TRACK_LISTENED_EVENT, trackEvent.get().getClass());

        final TrackListenedEvent trackListenedEvent = (TrackListenedEvent) trackEvent.get();
        assertEquals(TRACK_ID, trackListenedEvent.aggregateId());
        assertEquals(1, trackListenedEvent.listeningCounter().count());
    }

}