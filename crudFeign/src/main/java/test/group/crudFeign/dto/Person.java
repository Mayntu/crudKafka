package test.group.crudFeign.dto;













public class Person {
    private Long id;

    private String name;
    private String surname;

    private int age;

    private String gender;


    public Person() {

    }

    public Person(Long id, String name, String surname, int age, String gender) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }



    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }



    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getSurname() {
        return surname;
    }



    public void setAge(int age) {
        this.age = age;
    }


    public int getAge() {
        return age;
    }



    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}









