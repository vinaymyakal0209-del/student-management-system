import model.Student;
import service.StudentService;
import exception.*;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        Scanner sc = new Scanner(System.in);
        boolean runs = true;
        while(runs){
            try{
                System.out.println("1. Add student");
                System.out.println("2. Remove student");
                System.out.println("3. Find student");
                System.out.println("4. Update marks");
                System.out.println("5. List by name");
                System.out.println("6. List by marks (high to low)");
                System.out.println("7. Report");
                System.out.println("8. Exit");
                System.out.println("Choice a Option From MENU");
                int choice = sc.nextInt();
                switch(choice){
                    case 1: 
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    int marks = sc.nextInt();
                    try{
                        service.addStudent(new Student(id,name,marks));
                        System.out.println("Successfully added");
                    }catch(DuplicateStudentException | IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                    case 2:
                    System.out.print("Enter ID: ");
                    int r = sc.nextInt();
                    try{
                        service.removeStudent(r);
                        System.out.println("Successfully removed");
                    }catch(StudentNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                    case 3:
                    System.out.print("Enter ID: ");
                    int d = sc.nextInt();
                    try{
                        System.out.println(service.findStudent(d));
                    }catch(StudentNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                    case 4:
                    System.out.print("Enter ID: ");
                    int a = sc.nextInt();
                    System.out.print("Enter Marks: ");
                    int score = sc.nextInt();
                    try{
                        service.updateMarks(a, score);
                        System.out.println("Successfully Updated marks");
                    }catch(StudentNotFoundException | IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                    case 5:
                        service.getAllSorted(Comparator.comparing(Student::getName)).forEach(System.out::println);

                    break;
                    case 6:
                        service.getAllSorted(
                            Comparator.comparingInt(Student::getMarks).reversed()
                                .thenComparing(Student::getName)
                                ).forEach(System.out::println);

                    break;
                    case 7:
                    System.out.println("Top 3:");
                    service.getTopScorers(3).forEach(System.out::println);
                    System.out.printf("Average: %.2f%n", service.getAverageMarks());
                    System.out.println("Failed: " + service.countFailed());
                    break;
                    case 8: System.out.println("Exited Menu");
                    runs = false;
                    break;
                    default:System.out.println("Invalid choice");
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("Please enter a valid number");
                sc.nextLine();
            }
        }
    }
}