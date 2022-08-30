package my.site.ithoughtilearned.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "geo_data", schema = "itil_posts")
public class GeoData {
    @Id
    @Column(name = "pk", nullable = false)
    public UUID pk;

    @Column(name = "ip", nullable = false)
    public String ip;

    @Column(name = "lat" )
    public String lat;

    @Column(name = "lon" )
    public String lon;

    @Column(name = "meta")
    public String meta;

}
