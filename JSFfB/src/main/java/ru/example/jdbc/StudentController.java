package ru.example.jdbc;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@SessionScoped
@ManagedBean
public class StudentController {
    private List<Student> students;
    private StudentDbUtil studentDbUtil;
    private Logger logger = Logger.getLogger(getClass().getName());

    public StudentController() throws Exception {
        students = new ArrayList<>();
        studentDbUtil = StudentDbUtil.getInstance();
    }

    public void loadStudents() {
        logger.info("begin method");
        students.clear();
        try {
            students = studentDbUtil.getStudents();
        } catch (Exception e) {
            logger.severe("EX:error load students: " + e.getMessage());
            addErrorMessage(e);
        }
        logger.info("End method. Students = " + students.size());
    }

    private void addErrorMessage(Exception e) {
        final FacesMessage facesMessage = new FacesMessage("Error:" + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public List<Student> getStudents() {
        return students;
    }

    public String addStudent(Student student) {
        try {
            studentDbUtil.addStudent(student);
        } catch (SQLException e) {
            logger.severe("Error on insert: " + e.getMessage());
            addErrorMessage(e);
        }
        return "list_students?faces-redirect=true";
    }

    public String loadStudent(int id) {
        logger.info("Loading student id=" + id);
        try {
            Student student = studentDbUtil.getStudent(id);
            final ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
            ectx.getRequestMap().put("student", student);
        } catch (Exception e) {
            logger.severe("Error on load: " + e.getMessage());
            addErrorMessage(e);
            return null;
        }
        return "update_student_form";
    }

    public String updateStudent(Student student) {
        logger.info("Updating student " + student);
        try {
            studentDbUtil.updateStudent(student);
        } catch (Exception e) {
            logger.severe("Error on update: " + e.getMessage());
            addErrorMessage(e);
            return null;
        }
        return "list_students?faces-redirect=true";
    }
}
