import db.DBAlbum;
import db.DBArtist;
import models.Album;
import models.Artist;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DBAlbum.deleteAll();
        DBArtist.deleteAll();
        Artist artist1 = new Artist("AC/DC");

        DBArtist.save(artist1);

        Album album = new Album("Back in black", artist1,3);
        DBAlbum.save(album);


        Album album2 = new Album("Highway to hell", artist1,5);


        Artist artist2 = new Artist("Metallica");
        DBArtist.save(artist2);

        Album album3 = new Album("Ride the lightning", artist2, 9);
        DBAlbum.save(album3);

        Album album4 = new Album("Reload", artist2, 3);


    }
}
