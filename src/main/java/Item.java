import java.util.Arrays;

public class Item {
    public String name;
    public String[] desc;
    public Cost cost;
    public Properties[] properties;
    public double weight;
    public String url;

    public String toString() {
        String s;
        s = this.name + "\n" + Arrays.toString(this.desc) + "\n" +
                this.cost + "\n" + Arrays.toString(this.properties) +
                "\n" + this.weight + " pounds \n" + this.url;
        return s;
    }
}
