package impl;

import object.Cell;
import object.PathInfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class PathFinder {

    private int rowTracker;
    private int colTracker;
    private int index = -1;

    public PathInfo getPathInfo() throws Exception {

        LinkedList<PathInfo> pathInfoList = new LinkedList<>();
        PathInfo pathInfo = new PathInfo();
        PathInfo pathInfoCandidate = null;
        PathInfo pathInfoTemp;
        int[][] matrix = Matrix.getGivenMatrix();

        for(int row = 0; row < matrix.length; row++){
            for(int col = 0; col < matrix.length; col++){
                rowTracker = row;
                colTracker = col;
                List<PathInfo> pInfoList = new ArrayList<>();
                getPath(matrix, row, col, pInfoList, pathInfo);
                computeSteepAndLength(pInfoList, matrix);
                pathInfoTemp = pInfoList.stream().max(PathInfo::compareTo).orElse(null);

                if (pathInfoCandidate == null) {
                    pathInfoCandidate = pathInfoTemp;
                }

                if (pathInfoTemp != null && pathInfoTemp.compareTo(pathInfoCandidate) > 0) {
                    pathInfoCandidate = pathInfoTemp;
                }

                index = -1;
            }
        }

        return pathInfoCandidate;
    }

    private void computeSteepAndLength(List<PathInfo> pathInfoList, int[][] matrix) {
        for (PathInfo pathInfo : pathInfoList) {
            pathInfo.setLength(pathInfo.getCellList().size());
            int first = matrix[pathInfo.getCellList().get(0).getRow()][pathInfo.getCellList().get(0).getCol()];
            int last = matrix[pathInfo.getCellList().get(pathInfo.getLength() - 1).getRow()][pathInfo.getCellList().get(pathInfo.getLength() - 1).getCol()];
            pathInfo.setSteep(first - last);
        }
    }

    private void getPath(int[][] matrix, int row, int col, List<PathInfo> pInfoList, PathInfo pathInfo){
        PathInfo pInfo = pathInfo;
        PathInfo pInfoToAdd;
        int steep = 0;
        String direction = "none";
        Cell cell;

        cell = new Cell(row, col);

        if (pInfoList.isEmpty()) {
            pInfo = new PathInfo();
            pInfo.getCellList().add(cell);
            pInfoList.add(pInfo);
        } else {
            pInfoList.get(index).getCellList().add(cell);
        }

        if(col + 1 < matrix.length && col + 1 < 1000 && matrix[row][col + 1] < matrix[row][col]){
            direction = "right";
        }

        if(col - 1 >= 0 && matrix[row][col - 1] < matrix[row][col]){
            if (direction.equalsIgnoreCase("none")){
                direction = "left";
            } else {
                direction = direction.concat(",left");
            }
        }

        if(row + 1 < matrix.length && row + 1 < 1000 && matrix[row + 1][col] < matrix[row][col]){
            if (direction.equalsIgnoreCase("none")){
                direction = "down";
            } else {
                direction = direction.concat(",down");
            }

        }

        if(row - 1 >= 0 && matrix[row - 1][col] < matrix[row][col]){
            if (direction.equalsIgnoreCase("none")){
                direction = "up";
            } else {
                direction = direction.concat(",up");
            }
        }

        if (direction.split(",").length > 1) {
            pInfoToAdd = new PathInfo();
            pInfoToAdd.getCellList().addAll(pInfo.getCellList().subList(0, pInfo.getCellList().size()));
            for (int i = 0; i < direction.split(",").length - 1; i++){
                pInfoList.add(pInfoToAdd);
            }
            pInfo = pInfoToAdd;
        }

        if (direction.contains("right")) {
            if (row == rowTracker && col == colTracker){
                index++;
            }
            moveToNextCell("right", matrix, cell, pInfo, pInfoList);
        }

        if (direction.contains("left")) {
            if (row == rowTracker && col == colTracker){
                index++;
            }
            moveToNextCell("left", matrix, cell, pInfo, pInfoList);
        }

        if (direction.contains("down")) {
            if (row == rowTracker && col == colTracker){
                index++;
            }
            moveToNextCell("down", matrix, cell, pInfo, pInfoList);
        }

        if (direction.contains("up")) {
            if (row == rowTracker && col == colTracker){
                index++;
            }
            moveToNextCell("up", matrix, cell, pInfo, pInfoList);
        }


    }

    private void moveToNextCell(String direction, int[][] matrix, Cell cell, PathInfo pathInfo, List<PathInfo> pathInfoList){

        if(direction.equalsIgnoreCase("right")) {
            getPath(matrix, cell.getRow(), cell.getCol() + 1, pathInfoList, pathInfo);
        }else if(direction.equalsIgnoreCase("left")) {
            getPath(matrix, cell.getRow(), cell.getCol() - 1, pathInfoList, pathInfo);
        }else if(direction.equalsIgnoreCase("down")) {
            getPath(matrix, cell.getRow() + 1, cell.getCol(), pathInfoList, pathInfo);
        }else if(direction.equalsIgnoreCase("up")) {
            getPath(matrix, cell.getRow() - 1, cell.getCol(), pathInfoList, pathInfo);
        }
    }
}

