package be.vdab.muziekspringadvanced.domain;


import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "artiestid")
    private Artiest artiest;
    @ElementCollection
    @CollectionTable(name = "tracks", joinColumns = @JoinColumn(name = "albumid"))
    private Set<Track> tracks = new LinkedHashSet<>();

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Artiest getArtiest() {
        return artiest;
    }

    public Set<Track> getTracks() {
        return Collections.unmodifiableSet(tracks);
    }
}