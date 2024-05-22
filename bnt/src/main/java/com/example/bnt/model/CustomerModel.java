package com.example.bnt.model;

// import org.springframework.stereotype.Component;

// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Component
// @NoArgsConstructor
// @AllArgsConstructor
// @Getter
// @Setter
public class CustomerModel {
    private int id;
    private String name;
    private int sal;

   
    public int getId() {
        return id;
    }

   
    public void setId(int id) {
        this.id = id;
    }

   
    public String getName() {
        return name;
    }

   
    public void setName(String name) {
        this.name = name;
    }

    
    public int getSal() {
        return sal;
    }

   
    public void setSal(int sal) {
        this.sal = sal;
    }

    public CustomerModel() {
    }

   
    public CustomerModel(int id, String name, int sal) {
        this.id = id;
        this.name = name;
        this.sal = sal;
    }

}
