package my.site.ithoughtilearned.service;

import my.site.ithoughtilearned.model.Post;
import my.site.ithoughtilearned.model.Tag;
import my.site.ithoughtilearned.model.dto.PostDto;
import my.site.ithoughtilearned.model.dto.TagDto;
import my.site.ithoughtilearned.repository.PostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<String> getAllTags() {
        return postRepository.findAllTags();
    }

    public PostDto savePost(PostDto post) {
        return toDto(postRepository.save(toDb(post)));
    }

    public List<PostDto> getAllPosts() {
        List<Post> dbPosts = postRepository.findAll();
        return dbPosts.stream().map(post -> new PostDto(post.getPost_id(), post.getPost_content(), post.getPost_date(), post.getTags().stream().map(tag -> new TagDto(tag.getTag_id(), tag.getValue(), List.of())).collect(Collectors.toList()))).collect(Collectors.toList());

    }

    public List<PostDto> searchPostsByTag(String tag) {
        return postRepository.findAllPostsWithTag(tag).stream().map(this::toDto).collect(Collectors.toList());
    }

    private PostDto toDto(Post dbPost) {
        PostDto post = new PostDto();
        BeanUtils.copyProperties(dbPost, post);

        post.setTags(List.of());
        if (dbPost.getTags() != null) {
            post.setTags(dbPost.getTags().stream().map(tag -> new TagDto(tag.getTag_id(), tag.getValue(), List.of())).collect(Collectors.toList()));
        }

        return post;
    }

    private Post toDb(PostDto post) {
        Post dbPost = new Post();
        BeanUtils.copyProperties(post, dbPost);

        dbPost.setTags(List.of());
        if (post.getTags() != null) {
            dbPost.setTags(post.getTags().stream().map(tag -> new Tag(tag.getTag_id(), tag.getValue(), List.of())).collect(Collectors.toList()));
        }

        return dbPost;

    }

    public List<PostDto> searchPosts(String query) {
        return postRepository.findByPost_contentLike(query).stream().map(this::toDto).collect(Collectors.toList());
    }
}
