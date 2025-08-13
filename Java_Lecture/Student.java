class Student {
    String rollno;
    String name;
    int sem;
    String branch;
    int marks[];
    int credits[];
    double perc;
    double sgpa;

    Student() {
        rollno = null;
        branch = "Comp";
        sgpa = 0.0;
    }

    Student(String rollno, String name, int sem, String branch, int mks[]) {
        this.rollno = rollno;
        this.name = name;
        this.sem = sem;
        this.branch = branch;
        this.marks = mks;
        this.credits = new int[] { 4, 3, 3, 3, 3, 2 };
    }

    public void calcPerc() {
        int m = 0;
        for (int i = 0; i < 6; i++) {
            m += marks[i];
        }
        perc = (double) m / 6;
    }

    public void calcSGPA() {
        int m = 0;
        int c = 0;
        for (int i = 0; i < 6; i++) {
            int gradePoint = 0;
            if (marks[i] > 85)
                gradePoint = 10;
            else if (marks[i] > 75)
                gradePoint = 9;
            else if (marks[i] > 65)
                gradePoint = 8;
            else if (marks[i] > 55)
                gradePoint = 7;
            else if (marks[i] > 50)
                gradePoint = 6;
            else if (marks[i] > 45)
                gradePoint = 5;
            else if (marks[i] >= 40)
                gradePoint = 4;
            else
                gradePoint = 0;

            m += gradePoint * credits[i];
            c += credits[i];
        }
        sgpa = (double) m / c;
    }

}