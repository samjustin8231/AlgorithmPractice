package interview;

import java.util.Objects;

/**
 * @author sunyajun
 * @date 2020/3/3 4:42 PM
 */
public class ZooPos {

    private String name;

    private Integer x;
    private Integer y;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZooPos zooPos = (ZooPos) o;

        if (!x.equals(zooPos.x)) return false;
        return y.equals(zooPos.y);
    }

    @Override
    public int hashCode() {
        int result = x.hashCode();
        result = 31 * result + y.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.name + " " + this.x + " " + this.y;
    }
}
