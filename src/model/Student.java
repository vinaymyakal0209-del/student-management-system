package model;
import java.util.Objects;
public class Student {
    private int id;
    private String name;
    private int marks;
    public Student(int id, String name, int marks){
        this.id = id;
        this.name = name;
        if(marks < 0) throw new IllegalArgumentException("Marks can't be negative");
        this.marks=marks;
    }
    public String getName(){
        return this.name;
    }
    public int getId(){
        return this.id;
    }
    public int getMarks(){
        return this.marks;
    }
    public void setMarks(int marks){
        if(marks < 0){
            throw new IllegalArgumentException("Marks can't be negative");
        }
        this.marks = marks;

    }
    @Override 
    public String toString(){
         return this.id+" | "+this.name+" | "+this.marks;
    }
    @Override 
    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null || getClass()!=o.getClass()) return false;
        Student s = (Student) o;
        return id == s.id;
    }
    @Override 
    public int hashCode(){
        return Objects.hash(id);
    }



}
