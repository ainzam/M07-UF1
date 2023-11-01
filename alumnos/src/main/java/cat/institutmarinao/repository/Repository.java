package cat.institutmarinao.repository;

import java.util.List;

import javax.ejb.Local;

import cat.institutmarinao.alumnos.Student;

@Local
public interface Repository {
    List<String> getCycles();
    List<String> getModules(String cycle);
    List<Student> getStudents();
    void addStudent(Student student);
    void removeStudent(Student student);
}
