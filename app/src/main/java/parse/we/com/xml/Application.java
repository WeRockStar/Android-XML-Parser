package parse.we.com.xml;

/**
 * Created by Kotchaphan Muangsan on 20/8/2558.
 */
public class Application {

    private String name;
    private String artist;
    private String releaseDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String toString() {
        return "Name : " + this.name
                + "\nArtist : " + this.artist
                + "\nRelease Date : " + this.releaseDate
                + "\n";
    }
}
