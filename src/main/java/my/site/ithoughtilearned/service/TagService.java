package my.site.ithoughtilearned.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.site.ithoughtilearned.model.Tag;
import my.site.ithoughtilearned.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class TagService {

    @Autowired
    TagRepository tagRepository;


    public String saveTag(String tag) {
        return tagRepository.save(new Tag(null, tag.replaceAll("\"", ""), List.of())).getValue();
    }
}
