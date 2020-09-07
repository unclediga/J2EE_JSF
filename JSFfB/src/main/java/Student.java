import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class Student {
    private String firstName;
    private String lastName;
    private String country;
    private final List<String> countryOptions;

    public Student() {
        countryOptions = new ArrayList<String>();
        countryOptions.add("BR");
        countryOptions.add("FR");
        countryOptions.add("GR");
        countryOptions.add("IN");
        countryOptions.add("GB");
        countryOptions.add("US");

//        itemLabel="Brazil"/>
//                itemLabel="France"/>
//                itemLabel="Germany"/>
//                itemLabel="India"/>
//                itemLabel="United Kingdom"/>
//                itemLabel="United States"/>
    }

    public List<String> getCountryOptions() {
        return countryOptions;
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
}
