import impl.PathFinder;

public class Main {

    public static void main(String[] args){

        try {
            PathFinder pf = new PathFinder();
            pf.getPathInfo();
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
    }
}
