package my.site.ithoughtilearned.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private UUID post_id;
    private String post_content;
    private LocalDateTime post_date;
    private List<TagDto> tags;
}
