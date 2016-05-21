import java.util.ArrayList;
import java.util.Scanner;

class Range {
    int x,y;
    
    public Range(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
}

class MyArrayList<E> extends ArrayList<E> { // extended to override the protected behaviour of removeRange method
    
    public void removeRange(int fromIndex, int toIndex) {
        super.removeRange(fromIndex,toIndex);
    }
}

class MySolution {
    int min=0,max=0;
    MyArrayList<Range> list = new MyArrayList<>();
    public void findRange(int x, int y) {
        if(list.size() == 0) {
            list.add(new Range(x,y));
        }
        else if(x > max) {
            list.add(new Range(x,y));
        }
        else {
            int len = list.size();
            int flag=0,fromPos=0,fromX=0;
            for(int i = 0;i < len;i++) {
                int oldX = list.get(i).getX();
                int oldY = list.get(i).getY();
                if(x <= oldY) {
                    if(y <= oldY) {
                        if(flag==1) {
                            if(i+1 == list.size()) {
                                list.removeRange(fromPos,i+1);
                                list.add(i-1,new Range(fromX,oldY));
                            }else {
                                list.removeRange(fromPos,i+1);
                                list.add(i-1,new Range(fromX,oldY));
                                break;
                            }
                            flag=0;
                        }
                        else {
                            if(x > oldX && y < oldY) {
                                list.remove(i);
                                list.add(i,new Range(oldX,oldY));
                            }
                            else if(x > oldX && y > oldY) {
                                list.remove(i);
                                list.add(i,new Range(oldX,y));
                            }
                            else {
                                list.remove(i);
                                list.add(i,new Range(x,y));
                            }
                        }
                    }
                    else if(i+1 == len) {
                        list.remove(i);
                        list.add(i,new Range(oldX,y));
                    }
                    else {  //means y > oldY
                        flag=1;
                        fromPos = i;
                        fromX = oldX;
                    }
                }
            }
        }
        if(x < min) {
            min = x;
        }
        if(y > max) {
            max = y;
        }
        for(Range obj: list) {
            System.out.print("(" + obj.getX() + "," + obj.getY() + ")");
        }
        System.out.println();
    }
    public static void main(String args[]) {
        String x,y;
        Scanner in = new Scanner(System.in);
        MySolution solution = new MySolution();
        do {
            System.out.println("Enter X:");
            x = in.nextLine();
            System.out.println("Enter Y:");
            y = in.nextLine();
            if(x.equals("break") || y.equals("break")) {
                break;
            }
            solution.findRange(Integer.parseInt(x),Integer.parseInt(y));
        }while(true);
    }
}
