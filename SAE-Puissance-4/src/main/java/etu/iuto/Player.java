
public class Player {
    private String name;
    private String data;

    public Player(String name) {
        this.name = name;
        this.data = "";
    }

    public String getName() {
        return name;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "le joueur " + name + " a " + data;
    }
}