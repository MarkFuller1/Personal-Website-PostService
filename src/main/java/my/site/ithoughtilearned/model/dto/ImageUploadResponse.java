package my.site.ithoughtilearned.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageUploadResponse {
    UUID db_Id;
    String link;
}
