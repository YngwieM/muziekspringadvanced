package be.vdab.muziekspringadvanced.repositories;

import be.vdab.muziekspringadvanced.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
