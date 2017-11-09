package taskbook.tazahkahbar.com.taskbook2.Model;

/**
 * Created by lenovo on 11/9/2017.
 */

public class PostModel {
   public String   username , post;

    public PostModel(String username, String post) {
        this.username = username;
        this.post = post;
    }

    public String getUsername() {
        return username;
    }

    public String getPost() {
        return post;
    }
}
