package com.musiva.tracks.track.endpoint.request.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTrackRequest {
    private String name;
    private CreateTrackDataPathRequest trackDataPath;
}
