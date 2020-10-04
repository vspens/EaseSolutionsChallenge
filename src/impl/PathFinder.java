package impl;

import object.Cell;
import object.PathInfo;

import java.util.LinkedList;

public class PathFinder {

    public PathFinder() throws Exception {

        LinkedList<PathInfo> pathInfoList = new LinkedList<PathInfo>();
        PathInfo pathInfo;
        int[][] matrix = Matrix.GetGivenMatrix();

        for(int row = 0; row < matrix.length; row++){
            for(int col = 0; col < matrix.length; col++){
                pathInfo = new PathInfo();
                pathInfo = this.getPath(matrix, row, col, pathInfo);
                pathInfoList.add(pathInfo);
            }
        }

    }

    private PathInfo getPath(int[][] matrix, int row, int col, PathInfo pathInfo){
        PathInfo pInfo = pathInfo;
        int steep = 0;
        String direction = "right";

        Cell cell = new Cell(row, col);

        if(col + 1 < matrix.length && col + 1 < 1000 && matrix[row][col + 1] < matrix[row][col]){
            steep = matrix[row][col] - matrix[row][col+1];
            direction = "right";
            pInfo.setSteep(steep);
            pInfo.getCellList().add(cell);
            pInfo.setCellList(pInfo.getCellList());
            pInfo = moveToNextCell(direction, matrix, cell, pInfo);
        }

        if(col - 1 >= 0 && matrix[row][col - 1] < matrix[row][col]){
            steep = matrix[row][col] - matrix[row][col - 1];
            direction = "left";
            pInfo.setSteep(steep);
            pInfo.getCellList().add(cell);
            pInfo.setCellList(pInfo.getCellList());
            pInfo = moveToNextCell(direction, matrix, cell, pInfo);
        }

        if(row + 1 < matrix.length && row + 1 < 1000 && matrix[row + 1][col] < matrix[row][col]){
            steep = matrix[row][col] - matrix[row + 1][col];
            direction = "down";
            pInfo.setSteep(steep);
            pInfo.getCellList().add(cell);
            pInfo.setCellList(pInfo.getCellList());
            pInfo = moveToNextCell(direction, matrix, cell, pInfo);
        }

        if(row - 1 >= 0 && matrix[row - 1][col] < matrix[row][col]){
            steep = matrix[row][col] - matrix[row - 1][col];
            direction = "up";
            pInfo.setSteep(steep);
            pInfo.getCellList().add(cell);
            pInfo.setCellList(pInfo.getCellList());
            pInfo = moveToNextCell(direction, matrix, cell, pInfo);
        }

        return pathInfo;
    }

    private PathInfo moveToNextCell(String direction, int[][] matrix, Cell cell, PathInfo pathInfo){
        PathInfo pInfo = pathInfo;

        if(direction.equalsIgnoreCase("right")) {
            pInfo = getPath(matrix, cell.getRow(), cell.getCol() + 1, pathInfo);
        }else if(direction.equalsIgnoreCase("left")) {
            pInfo = getPath(matrix, cell.getRow(), cell.getCol() - 1, pathInfo);
        }else if(direction.equalsIgnoreCase("down")) {
            pInfo = getPath(matrix, cell.getRow() + 1, cell.getCol(), pathInfo);
        }else if(direction.equalsIgnoreCase("up")) {
            pInfo = getPath(matrix, cell.getRow() - 1, cell.getCol(), pathInfo);
        }

        return pInfo;
    }
}

