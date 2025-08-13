
class Student{
    int rollno;
    void getNumber(int n){
        rollno=n;
    }
    void putNumber(){
        System.out.println("Roll no:" +rollno);
    }
}

class Test extends Student{
    float part1,part2;
    void getMarks(float a,float b){
        part1=a;
        part2=b;
    }
    void putMarks(){
        System.out.println("Marks Part1:"+part1 +" Part2:"+part2);
    }
}

interface Sports {
    float sportswt=6.0f;
    void putwt();
}

class Result extends Test implements Sports {
    float total;
    public void putwt(){
        System.out.println("Sports wt "+sportswt);
    }
    void display(){
        total=part1+part2+sportswt;
        putNumber();
        putMarks();
        putwt();
        System.out.println("Total = "+total);
    }
}

public class LabLec1_1 {
    public static void main(String[] args) {
        Result r=new Result();
        r.getMarks(80.4f, 78.9f);
        r.getNumber(44);
        r.display();
    }
}
