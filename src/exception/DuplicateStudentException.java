package exception;

public class DuplicateStudentException extends Exception{
    public DuplicateStudentException(String msg){
        super(msg);
    }
}
