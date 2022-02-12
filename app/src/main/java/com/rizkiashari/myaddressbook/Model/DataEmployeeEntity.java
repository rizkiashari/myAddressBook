package com.rizkiashari.myaddressbook.Model;

public class DataEmployeeEntity {

    private Integer id;
    private String name, city, phone, email, picture;

    public DataEmployeeEntity(Integer id, String phone, String name, String city, String email, String picture){
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.city = city;
        this.email = email;
        this.picture = picture;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }
}
