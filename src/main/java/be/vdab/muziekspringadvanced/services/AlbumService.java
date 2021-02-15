package be.vdab.muziekspringadvanced.services;

import be.vdab.muziekspringadvanced.domain.Album;

import java.util.Optional;

public interface AlbumService {
    Optional<Album> findById(long id);

}
