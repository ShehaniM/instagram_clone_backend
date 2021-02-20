package lk.ijse.instagramclone.repository;

import javafx.geometry.Pos;
import lk.ijse.instagramclone.entity.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author : Yasindu Sathsara <yasindu@ijse.lk> (ijse.lk)
 * @since : 12/19/20
 **/
public interface PostRepo extends CrudRepository<Post, Integer> {
    @Query(
            value = "SELECT * FROM post where user_id = ?1",
            nativeQuery = true)
    List<Post> findAllPostByUser(Integer id);
}
