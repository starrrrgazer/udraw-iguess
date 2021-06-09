package ui.part;
import java.util.ArrayList;
public class PointList {
    private static PointList pointList=new PointList();

    private PointList(){

    }

    public static PointList getPointList(){
        return pointList;
    }

    public ArrayList<Point> getList() {
        return list;
    }

    ArrayList<Point> list=new ArrayList<Point>();

}
