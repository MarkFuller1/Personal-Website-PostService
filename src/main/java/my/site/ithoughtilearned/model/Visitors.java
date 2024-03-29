package my.site.ithoughtilearned.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "visitors", schema = "itil_posts")
@NoArgsConstructor
public class Visitors {
    @Id
    @Column(name = "pk", nullable = false)
    @NonNull
    public UUID pk;
    @Column(name = "ip", nullable = false)
    @NonNull
    public String ip;

    @Column(name = "time", nullable = false)
    @NonNull
    public String time;
}