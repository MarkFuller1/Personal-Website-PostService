package my.site.ithoughtilearned.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "posts", schema = "itil_posts")
public class Post {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "post_id", nullable = false)
    private UUID post_id;

    @Column(name = "post_content", nullable = false)
    private String post_content;

    @Column(name = "post_date", nullable = false)
    private LocalDateTime post_date;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "POST_TAGS", joinColumns =
    @JoinColumn(name = "post_id", referencedColumnName = "post_id"), inverseJoinColumns =
    @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")
    )
    private List<Tag> tags;
}
