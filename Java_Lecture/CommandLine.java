public class CommandLine {
    public static void main(String[] args) {
        int count,i=0;
        count=args.length;
        System.out.println("No of args: "+count);
        while(i<count) {
            System.out.println("Arg "+(i+1)+": "+args[i]);
            i++;
        }
    }
}
