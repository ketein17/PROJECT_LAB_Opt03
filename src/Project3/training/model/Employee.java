package Project3.training.model;

import Project3.training.utils.DateFormatException;
import Project3.training.utils.EmailFormatException;
import Project3.training.utils.PhoneNumberFormatException;
import Project3.training.utils.Validator;

public abstract class Employee {
    protected static String ssn="0";
    protected String firstName,lastName;
    protected String birthDate;
    protected String phone;
    protected String email;

    public Employee(){};

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
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




    public String getBirthDate(){
        return birthDate;
    }

    public void setBirthDate(String birthDate) throws DateFormatException {
        if(Validator.isDate(birthDate)){
            this.birthDate=birthDate;
        }
        else{
            throw new DateFormatException("Date is invalid");
        }
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone) throws PhoneNumberFormatException {
        if(Validator.isPhoneNumber(phone)){
            this.phone=phone;
        }
        else{
            throw new PhoneNumberFormatException("Phone number is invalid");
        }
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) throws EmailFormatException {
        if(Validator.isEmail(email)){
            this.email=email;
        }
        else{
            throw new EmailFormatException("Email is invalid");
        }
    }

    public abstract void display();


}
