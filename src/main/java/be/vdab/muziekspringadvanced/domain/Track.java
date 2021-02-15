package be.vdab.muziekspringadvanced.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
@Access(AccessType.FIELD)
public class Track {
    private String naam;
    private LocalTime tijd;

    public String getNaam() {
        return naam;
    }

    public LocalTime getTijd() {
        return tijd;
    }

    @Override
    public int hashCode() {
        return naam.toUpperCase().hashCode();
    }
    @Override
    public boolean equals(Object object) {
        if ((object instanceof Track)) {
            var that = (Track) object;
            return naam.equalsIgnoreCase(that.naam);
        }
        return false;
    }
}