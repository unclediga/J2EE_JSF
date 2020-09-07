import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class Student {
    private String firstName;
    private String lastName;
    private String country;
    private final List<String> countryOptions;
    private final List<String> languageOptions;
    private String[] favLangs;
    private String email;
    private String phoneNumber;
    private String postalCode;
    private int freePasses;
    private String courseCode;

    public Student() {
        countryOptions = new ArrayList<String>();
        countryOptions.add("BR");
        countryOptions.add("FR");
        countryOptions.add("GR");
        countryOptions.add("IN");
        countryOptions.add("GB");
        countryOptions.add("US");

        languageOptions = new ArrayList<String>();
        languageOptions.add("Java");
        languageOptions.add("C#");
        languageOptions.add("Haskell");
        languageOptions.add("JavaScript");
//        itemLabel="Brazil"/>
//                itemLabel="France"/>
//                itemLabel="Germany"/>
//                itemLabel="India"/>
//                itemLabel="United Kingdom"/>
//                itemLabel="United States"/>
    }

    public String[] getFavLangs() {
        return favLangs;
    }

    public void setFavLangs(String[] favLangs) {
        this.favLangs = favLangs;
    }

    public List<String> getCountryOptions() {
        return countryOptions;
    }

    public List<String> getLanguageOptions() {
        return languageOptions;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(int freePasses) {
        this.freePasses = freePasses;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void validateCourseCode(FacesContext context,
                                   UIComponent component, Object value) throws ValidatorException {
        if (value != null) {
            String data = (String) value;
            if (!data.startsWith("IT")) {
                final FacesMessage message = new FacesMessage("Course code must begin with IT");
                throw new ValidatorException(message);
            }
        }
    }
}
