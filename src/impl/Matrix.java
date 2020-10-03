package impl;

import java.io.File;
import java.util.Scanner;

public class Matrix {

    public static int[][] GetGivenMatrix() throws Exception{
        int[][] matrix ;

        Scanner scan = new Scanner(new File("res/matrix.txt"));

        String line = null;
        int col = 0;
        int row = 0;
        int size = Integer.parseInt(scan.next());
        matrix = new int[size][size];

        scan.nextLine();
        while (scan.hasNext()) {

            if(col != size){
                col++;
                int num = Integer.parseInt(scan.next());
                matrix[row][col - 1] = num;
            }else {
                col = 0;
                row++;
                scan.nextLine();
            }
        }


        return matrix;
    }
}
