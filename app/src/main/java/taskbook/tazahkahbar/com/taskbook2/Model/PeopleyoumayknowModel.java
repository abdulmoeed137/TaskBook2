package taskbook.tazahkahbar.com.taskbook2.Model;

/**
 * Created by lenovo on 11/9/2017.
 */

public class PeopleyoumayknowModel {

    private String id, username ,image;

    public PeopleyoumayknowModel(String id, String username, String image) {
        this.id = id;
        this.username = username;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }
}
