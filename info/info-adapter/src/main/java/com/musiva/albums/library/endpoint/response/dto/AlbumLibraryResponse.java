package com.musiva.albums.library.endpoint.response.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlbumLibraryResponse {
    private UUID id;
    private UUID userId;
    private Set<UUID> albums;
}