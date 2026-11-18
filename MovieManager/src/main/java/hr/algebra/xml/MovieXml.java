package hr.algebra.xml;

import hr.algebra.model.Movie;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "movie")
@XmlAccessorType(XmlAccessType.FIELD)
public class MovieXml {

    @XmlElement
    private int id;

    @XmlElement
    private String title;

    @XmlElement
    private String link;

    @XmlElement
    private String description;

    @XmlElement(name = "picture_path")
    private String picturePath;

    @XmlElement(name = "published_date")
    private String publishedDate;

    public MovieXml() {
    }

    public static MovieXml from(Movie m) {
        MovieXml x = new MovieXml();
        x.id = m.getId();
        x.title = m.getTitle();
        x.link = m.getLink();
        x.description = m.getDescription();
        x.picturePath = m.getPicturePath();
        x.publishedDate = m.getPublishedDate() != null ? m.getPublishedDate().toString() : null;
        return x;
    }
}
