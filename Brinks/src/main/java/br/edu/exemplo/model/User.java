package br.edu.exemplo.model;

// atributos, construtores, getters e setters do user
public class User {
    private int cod;
    private String username;
    private String password;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int cod, String username, String password) {
        this.cod = cod;
        this.username = username;
        this.password = password;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [cod=" + cod + ", username=" + username + "]";
    }
}
