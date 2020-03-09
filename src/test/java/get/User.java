package get;

public class User {
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;


    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

     @Override
    public String toString() {
        return String.format("[name:%s; job:%s; id:%s; createdAt:%s]",
                first_name, id );
    }

}
