package lk.ijse.instagramclone.repository;

import lk.ijse.instagramclone.entity.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepo extends CrudRepository<Comment, Integer> {
    List<Comment> findAllByPostId(Integer id);
}
