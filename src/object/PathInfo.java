package object;

import java.util.LinkedList;

public class PathInfo implements Comparable<PathInfo> {
    private int length;
    private int steep;
    private LinkedList<cell> cellList;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSteep() {
        return steep;
    }

    public void setSteep(int steep) {
        this.steep = steep;
    }

    public LinkedList<cell> getCellList() {
        return cellList;
    }

    public void setCellList(LinkedList<cell> cellList) {
        this.cellList = cellList;
    }

    @Override
    public int compareTo(PathInfo o) {
        return 0;
    }
}
