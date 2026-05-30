package eu.ase.iojson;

import org.json.JSONObject;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String email;
    private String password;

    public User(int id, String email, String password, String text) {
        this.id = id;
        this.email = email;
        this.password = password;

    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJsonString() {
        JSONObject dataset = new JSONObject();
        try {
            dataset.put("id", this.id);
            dataset.put("email", this.email);
            dataset.put("password", this.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataset.toString();
    }
}
