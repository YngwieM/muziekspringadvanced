package be.vdab.muziekspringadvanced.restcontrollers;

import be.vdab.muziekspringadvanced.domain.Album;
import be.vdab.muziekspringadvanced.domain.Track;
import be.vdab.muziekspringadvanced.dto.AlbumArtiest;
import be.vdab.muziekspringadvanced.exceptions.AlbumNietGevondenException;
import be.vdab.muziekspringadvanced.services.AlbumService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("albums")
@ExposesResourceFor(Album.class)
@CrossOrigin
class AlbumController {
    private final AlbumService albumService;
    private final EntityLinks entityLinks;

    public AlbumController(AlbumService albumService, EntityLinks entityLinks) {
        this.albumService = albumService;
        this.entityLinks = entityLinks;
    }

    @GetMapping("{id}")
    EntityModel<AlbumArtiest> getAlbum(@PathVariable long id) {
        var optionalAlbum = albumService.findById(id);
        if (optionalAlbum.isPresent()) {
            var album = optionalAlbum.get();
            var albumArtiest = new AlbumArtiest(album);
            var model = new EntityModel<>(albumArtiest);
            model.add(entityLinks.linkToItemResource(Album.class, id));
            model.add(entityLinks.linkForItemResource(Album.class, id)
                    .slash("tracks").withRel("tracks"));
            return model;
        }
        throw new AlbumNietGevondenException();
    }
    @GetMapping("{id}/tracks")
    Set<Track> getTracks(@PathVariable long id) {
        var optionalAlbum = albumService.findById(id);
        if (optionalAlbum.isPresent()) {
            return optionalAlbum.get().getTracks();
        }
        throw new AlbumNietGevondenException();
    }
    @ExceptionHandler(AlbumNietGevondenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void albumNietGevonden() {
    }
}