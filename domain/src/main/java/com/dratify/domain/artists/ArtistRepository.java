package com.dratify.domain.artists;

import domain.DomainRepository;
import java.util.UUID;

interface ArtistRepository extends DomainRepository<UUID, Artist> { }
