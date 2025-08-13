public class Result {
    public static void main(String[] args) {
        Student s1 = new Student("101", "Alice", 1, "Comp", new int[] { 85, 90, 78, 92, 88, 76 });
        s1.calcPerc();
        s1.calcSGPA();
        System.out.println("Percentage: " + s1.perc);
        System.out.println("SGPA: " + s1.sgpa);
        Student s2 = new Student("102", "Bob", 1, "Comp", new int[] { 80, 85, 75, 90, 82, 88 });
        s2.calcPerc();
        s2.calcSGPA();
        System.out.println("Percentage: " + s2.perc);
        System.out.println("SGPA: " + s2.sgpa);
    }
}
