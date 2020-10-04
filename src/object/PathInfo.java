package object;

import java.util.LinkedList;

public class PathInfo implements Comparable<PathInfo> {
    private int length = 0;
    private int steep = 0;
    private LinkedList<Cell> cellList = new LinkedList<Cell>();

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

    public LinkedList<Cell> getCellList() {
        return cellList;
    }

    public void setCellList(LinkedList<Cell> cellList) {
        this.cellList = cellList;
    }

    @Override
    public int compareTo(PathInfo pathInfo) {
        int ret = -1;

        if(this.length > pathInfo.length && this.steep > pathInfo.steep){
            ret = 1;
        }

        return ret;
    }
}
