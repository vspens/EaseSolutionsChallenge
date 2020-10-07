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
                Cell cell = new Cell(row, col);
                pathInfo.getCellList().add(cell);
                pathInfo.setCellList(pathInfo.getCellList());
                pathInfo = this.getPath(matrix, row, col, pathInfo);
                pathInfoList.add(pathInfo);
            }
        }

    }

    private PathInfo getPath(int[][] matrix, int row, int col, PathInfo pathInfo){
        PathInfo pInfoRight = pathInfo;
        PathInfo pInfoLeft = pathInfo;
        PathInfo pInfoDown = pathInfo;
        PathInfo pInfoUp = pathInfo;
        int steep = 0;
        String direction = "right";
        Cell cell;

        if(col + 1 < matrix.length && col + 1 < 1000 && matrix[row][col + 1] < matrix[row][col]){
            steep = matrix[row][col] - matrix[row][col+1];
            cell = new Cell(row, col + 1);
            direction = "right";
            pInfoRight.setSteep(steep);
            pInfoRight.getCellList().add(cell);
            pInfoRight.setCellList(pInfoRight.getCellList());
            pInfoRight = moveToNextCell(direction, matrix, cell, pInfoRight);
        }

        if(col - 1 >= 0 && matrix[row][col - 1] < matrix[row][col]){
            steep = matrix[row][col] - matrix[row][col - 1];
            cell = new Cell(row, col - 1);
            direction = "left";
            pInfoLeft.setSteep(steep);
            pInfoLeft.getCellList().add(cell);
            pInfoLeft.setCellList(pInfoLeft.getCellList());
            pInfoLeft = moveToNextCell(direction, matrix, cell, pInfoLeft);
        }

        if(row + 1 < matrix.length && row + 1 < 1000 && matrix[row + 1][col] < matrix[row][col]){
            steep = matrix[row][col] - matrix[row + 1][col];
            cell = new Cell(row + 1, col);
            direction = "down";
            pInfoDown.setSteep(steep);
            pInfoDown.getCellList().add(cell);
            pInfoDown.setCellList(pInfoDown.getCellList());
            pInfoDown = moveToNextCell(direction, matrix, cell, pInfoDown);
        }

        if(row - 1 >= 0 && matrix[row - 1][col] < matrix[row][col]){
            steep = matrix[row][col] - matrix[row - 1][col];
            cell = new Cell(row - 1, col);
            direction = "up";
            pInfoUp.setSteep(steep);
            pInfoUp.getCellList().add(cell);
            pInfoUp.setCellList(pInfoUp.getCellList());
            pInfoUp = moveToNextCell(direction, matrix, cell, pInfoUp);
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

