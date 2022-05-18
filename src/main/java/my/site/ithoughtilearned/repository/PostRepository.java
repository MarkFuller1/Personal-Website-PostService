package my.site.ithoughtilearned.repository;

import my.site.ithoughtilearned.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends CrudRepository<Post, UUID>{

    @Query(nativeQuery = true, value = "select * from itil_posts.posts order by post_date desc")
    public List<Post> findAll();

    @Query(nativeQuery = true, value = "SELECT *\n" +
            "FROM\n" +
            "\t(SELECT POST_ID,\n" +
            "\t\t\tPOST_CONTENT,\n" +
            "\t\t\tPOST_DATE,\n" +
            "\t\t\tJ1.TAG_ID,\n" +
            "\t\t\tVALUE\n" +
            "\t\tFROM\n" +
            "\t\t\t(SELECT ITIL_POSTS.POST_TAGS.POST_ID,\n" +
            "\t\t\t\t\tPOST_CONTENT,\n" +
            "\t\t\t\t\tPOST_DATE,\n" +
            "\t\t\t\t\tTAG_ID,\n" +
            "\t\t\t\t\tPOST_TAGS_ID\n" +
            "\t\t\t\tFROM ITIL_POSTS.POSTS\n" +
            "\t\t\t\tJOIN ITIL_POSTS.POST_TAGS ON ITIL_POSTS.POSTS.POST_ID = ITIL_POSTS.POST_TAGS.POST_ID) AS J1\n" +
            "\t\tJOIN ITIL_POSTS.TAGS ON J1.TAG_ID = ITIL_POSTS.TAGS.TAG_ID) AS POST_TAG_COMBO\n" +
            "WHERE VALUE = ?1")
    List<Post> findAllPostsWithTag(String value);

    @Query(nativeQuery = true, value = "select distinct(value) from itil_posts.tags")
    List<String> findAllTags();

    List<Post> findByTags_Value(String value);

    @Query(nativeQuery = true, value = "SELECT * FROM ITIL_POSTS.POSTS where LOWER(POST_CONTENT) like CONCAT('%',LOWER(:post_content),'%') order by POST_DATE desc")
    List<Post> findByPost_contentLike(@Param("post_content") String post_content);
}
