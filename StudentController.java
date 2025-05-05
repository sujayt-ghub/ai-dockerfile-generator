// Import necessary classes
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


// Controller class to handle REST API endpoints
@RestController
class StudentController {

    @Autowired
    private StudentDao studentDao;

    // Get all students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    // Get a single student by ID
    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable int id) {
        Student student = studentDao.getStudentById(id);
        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        return student;
    }

    // Add a new student
    @PostMapping("/students")
    public void addStudent(@Valid @RequestBody Student student) {
        studentDao.addStudent(student);
    }

    // Update an existing student
    @PutMapping("/students/{id}")
    public void updateStudent(@PathVariable int id, @Valid @RequestBody Student student) {
        Student existingStudent = studentDao.getStudentById(id);
        if (existingStudent == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        studentDao.updateStudent(id, student);
    }

    // Delete a student by ID
    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable int id) {
        Student existingStudent = studentDao.getStudentById(id);
        if (existingStudent == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
        }
        studentDao.deleteStudent(id);
    }
}

