package my.site.ithoughtilearned.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {
    private UUID tag_id;
    private String value;
    private List<PostDto> posts;

}
