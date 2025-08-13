
class Factorial{

    int Fact(int n){
        if(n==0) return 1;
        return n*Fact(n-1);
    }

    public static void main(String[] args) {
        int n=5;
        Factorial f=new Factorial();
        int fact=f.Fact(n);
        System.err.println(fact);
        
    }
}