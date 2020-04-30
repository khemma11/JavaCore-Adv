package model;

public class User {
    private String name;
    private String surName;
    private Gender gender;
    private int age;
    private String phonNumber;
    private String password;

    public User(String name, String surName, Gender gender, int age, String phonNumber, String password) {
        this.name = name;
        this.surName = surName;
        this.gender = gender;
        this.age = age;
        this.phonNumber = phonNumber;
        this.password = password;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhonNumber() {
        return phonNumber;
    }

    public void setPhonNumber(String phonNumber) {
        this.phonNumber = phonNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surName != null ? !surName.equals(user.surName) : user.surName != null) return false;
        if (gender != user.gender) return false;
        if (phonNumber != null ? !phonNumber.equals(user.phonNumber) : user.phonNumber != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (phonNumber != null ? phonNumber.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name=" + name + " " +
                ", surName=" + surName + " " +
                ", gender=" + gender +
                ", age=" + age +
                ", phonNumber=" + phonNumber + " " +
                ", password= " + password + " " +
                '}';
    }
}
