import impl.Matrix;
import impl.PathFinder;
import object.Cell;
import object.PathInfo;

public class Main {

    public static void main(String[] args){

        try {
            int[][] matrix = Matrix.getGivenMatrix();
            PathFinder pf = new PathFinder();
            PathInfo pathInfo = pf.getPathInfo(matrix);

            System.out.println("Length of the calculated path: " + pathInfo.getLength());
            System.out.println("Drop of the calculated path: " + pathInfo.getSteep());
            for (Cell cell : pathInfo.getCellList()) {
                System.out.println(matrix[cell.getRow()][cell.getCol()]);
            }
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
    }
}
