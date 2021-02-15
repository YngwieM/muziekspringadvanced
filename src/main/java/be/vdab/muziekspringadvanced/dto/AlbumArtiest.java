package be.vdab.muziekspringadvanced.dto;

import be.vdab.muziekspringadvanced.domain.Album;

public class AlbumArtiest {
    private final String album;
    private final String artiest;
    public AlbumArtiest(Album album) {
        this.album = album.getNaam();
        this.artiest = album.getArtiest().getNaam();
    }

    public String getAlbum() {
        return album;
    }

    public String getArtiest() {
        return artiest;
    }
}
