package akc8.cs262.calvin.edu.homework2;

public class GameItem {
    private int id;
    private String name;
    private String email;

    public GameItem(int id, String name, String email) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public String getString() {
        return Integer.toString(id) + "/ " + name + "/ " + email;
    }
}
