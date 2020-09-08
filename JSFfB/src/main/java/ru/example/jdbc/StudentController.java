package ru.example.jdbc;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@SessionScoped
@ManagedBean
public class StudentController {

    private List<Student> students;
    private final StudentDbUtil dbUtil;
    private final Logger log = Logger.getLogger(getClass().getName());

    public StudentController() {
        dbUtil = StudentDbUtil.getInstance();
        students = new ArrayList<>();
    }

    public void loadStudents() {
        students.clear();
        try {
            students = dbUtil.getStudents();
        } catch (Exception e) {
            log.severe("EX:error load students: " + e.getMessage());
            addErrorMessage(e);
        }
    }

    private void addErrorMessage(Exception e) {
        final FacesMessage facesMessage = new FacesMessage("Error:" + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public List<Student> getStudents() {
        if (students == null) {
            loadStudents();
        }
        return students;
    }
}
