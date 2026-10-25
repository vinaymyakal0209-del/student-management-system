package service;

import model.Student;
import exception.DuplicateStudentException;
import exception.StudentNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class StudentService {
    private static final int PASS_MARK = 40;
    private final Map<Integer, Student> mapstudent = new HashMap<>();
    public void addStudent(Student s) throws DuplicateStudentException{
        if (mapstudent.containsKey(s.getId())) throw new DuplicateStudentException("Student with ID " +s.getId()+ " already exists");
        mapstudent.put(s.getId(), s);
    }
    public void removeStudent(int id) throws StudentNotFoundException{
        if (mapstudent.containsKey(id)) mapstudent.remove(id);
        else throw new StudentNotFoundException("Student with ID " + id + " not found");
    }
    public Student findStudent(int id) throws StudentNotFoundException{
        Student s = mapstudent.get(id);
        if(s==null){
            throw new StudentNotFoundException("Student with ID " + id + " not found");
        }
        return s;
    }
    public void updateMarks(int id, int marks) throws StudentNotFoundException{
        Student s = findStudent(id);
        s.setMarks(marks);
    }
    public List<Student> getAllSorted(Comparator<Student> c){
        List<Student> sortedStudent = new ArrayList<>(mapstudent.values());
        sortedStudent.sort(c);
        return sortedStudent;
    }
    public List<Student> getTopScorers(int n){
        return  mapstudent.values().stream()
        .sorted(Comparator.comparingInt(Student::getMarks).reversed().thenComparing(Student::getName))
        .limit(n)
        .toList();
    }
    public double getAverageMarks(){
        return mapstudent.values().stream()
        .mapToInt(s -> s.getMarks())
        .average()
        .orElse(0);
    }
    public long countFailed(){
        return mapstudent.values().stream()
        .filter(s -> s.getMarks() < PASS_MARK)
        .count();
    }


}
