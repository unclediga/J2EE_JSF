import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@ManagedBean
public class DataUtil {
    private List<Student> students;

    public DataUtil() {
        students = new ArrayList<Student>();
        loadSampleData();
    }

    private void loadSampleData() {
        students.add(new Student("Ivanov","Ivan", "ii@mail.ru"));
        students.add(new Student("Petrov","Peter", "pp@mail.ru"));
        students.add(new Student("Sidorov","Sidor", "ss@mail.ru"));
    }

    public List<Student> getStudents() {
        return students;
    }
}
