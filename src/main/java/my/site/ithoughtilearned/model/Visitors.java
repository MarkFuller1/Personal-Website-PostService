package my.site.ithoughtilearned.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Table(name = "visitors", schema = "itil_posts")
@NoArgsConstructor
public class Visitors {
    @Id
    @Column(name="pk", nullable = false)
    UUID pk;
    @Column(name = "ip", nullable = false)
    public String ip;

    @Column(name = "time", nullable = false)
    public String time;
}