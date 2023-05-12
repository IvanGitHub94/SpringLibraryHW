package com.jpcnew.SpringLibraryHW.dbexample.model;

import lombok.*;

//import java.util.Date;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String surname;
    private String name;
    private Date birthDate;
    private Long phoneNumber;
    private String email;
    //private List<String> titles;
}
