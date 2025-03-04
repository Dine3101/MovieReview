package org.pjadm.movie_review.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.stereotype.Component;

import java.util.List;


@Document(collection="movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    private ObjectId id;
    private String imbdId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private List<String> genres;
    private String poster;
    private List<String> backdrops;

    @DocumentReference
    private List<Review> reviewIds;
}
