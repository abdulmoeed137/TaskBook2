package taskbook.tazahkahbar.com.taskbook2.Model;

/**
 * Created by lenovo on 11/9/2017.
 */

public class PostModel {
  public  String   username , post , status,post_id,user_id,comment,like,share;
   public boolean isLike ;

    public PostModel(String username, String post, String status) {
        this.username = username;
        this.post = post;
        this.status = status;
    }

    public PostModel(String username, String post, String post_id, String like, String comment) {
        this.username = username;
        this.post = post;
        this.post_id = post_id;
        this.like = like;
        this.comment = comment;

    }
    public String getUsername() {
        return username;
    }

    public String getPost() {
        return post;
    }

    public String getStatus() {
        return status;
    }

    public String getLike() {
        return like;
    }

    public String getComment() {
        return comment;
    }

    public String getPost_id() {
        return post_id;
    }
    public  boolean getIsLike(){
        return isLike;
    }
    public void setIsLike(boolean isLike)
    {
        this.isLike = isLike;
    }
}
