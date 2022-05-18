package my.site.ithoughtilearned.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tags", schema = "itil_posts")
public class Tag {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "tag_id")
    UUID tag_id;

    @Column(name = "value")
    String value;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "tags")
    public List<Post> posts;
}
