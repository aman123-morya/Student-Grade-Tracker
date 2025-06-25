import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private ArrayList<Integer> grades;

    public Student(String name) {
        this.name = name;
        grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    public double getAverage() {
        if (grades.isEmpty()) return 0;
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return Math.round((double) sum / grades.size() * 100.0) / 100.0;
    }

    public int getHighest() {
        if (grades.isEmpty()) return 0;
        int max = grades.get(0);
        for (int grade : grades) {
            if (grade > max) max = grade;
        }
        return max;
    }

    public int getLowest() {
        if (grades.isEmpty()) return 0;
        int min = grades.get(0);
        for (int grade : grades) {
            if (grade < min) min = grade;
        }
        return min;
    }

    public ArrayList<Integer> getGrades() {
        return grades;
    }

    public void displaySummary() {
        System.out.println("+------------------------------------------+");
        System.out.printf("| Student Name : %-26s |\n", name);
        System.out.printf("| Grades       : %-26s |\n", grades.toString());
        System.out.printf("| Average      : %-26.2f |\n", getAverage());
        System.out.printf("| Highest      : %-26d |\n", getHighest());
        System.out.printf("| Lowest       : %-26d |\n", getLowest());
        System.out.println("+------------------------------------------+");
    }
}

public class StudentGradeTracker {
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printHeader();
        enterStudentData();
        printAllSummaries();
    }

    private static void printHeader() {
        System.out.println("==========================================");
        System.out.println("       🌟 STUDENT GRADE TRACKER 🌟         ");
        System.out.println("==========================================");
    }

    private static void enterStudentData() {
        while (true) {
            System.out.print("Enter student name (or type 'done' to finish): ");
            String name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("done")) break;

            Student student = new Student(name);

            while (true) {
                System.out.print("  ➤ Enter grade for " + name + " (-1 to stop): ");
                int grade;
                try {
                    grade = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("   Invalid input! Please enter a number.");
                    continue;
                }
                if (grade == -1) break;
                if (grade < 0 || grade > 100) {
                    System.out.println("   Grade must be between 0 and 100.");
                    continue;
                }
                student.addGrade(grade);
            }

            studentList.add(student);
            System.out.println(" " + name + "'s data saved.\n");
        }
    }

    private static void printAllSummaries() {
        System.out.println("\n==========================================");
        System.out.println("           📊 STUDENT REPORTS 📊           ");
        System.out.println("==========================================");

        for (Student student : studentList) {
            student.displaySummary();
        }

        System.out.println("========== 📋 END OF REPORT 📋 ============\n");
    }
}
