package lk.ijse.instagramclone.dto;

public class CommentDto {
    private Integer postId;
    private Integer userId;
    private String comment;

    public CommentDto() {
    }

    public CommentDto(String comment) {
        this.comment = comment;
    }

    public CommentDto(Integer postId, Integer userId, String comment) {
        this.postId = postId;
        this.userId = userId;
        this.comment = comment;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
