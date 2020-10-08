import impl.PathFinder;
import object.PathInfo;

public class Main {

    public static void main(String[] args){

        try {
            PathFinder pf = new PathFinder();
            PathInfo pathInfo = pf.getPathInfo();

            System.out.println("Length of the calculated path: " + pathInfo.getLength());
            System.out.println("Drop of the calculated path: " + pathInfo.getSteep());
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
    }
}
