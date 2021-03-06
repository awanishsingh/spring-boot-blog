/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Cam Luc
 */
@Entity
@Data
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @NotNull(message = "User name cannot be null")
    @Size(min = 3, message = "User name is too short")
    private String name;
    
    @NotNull(message = "User email cannot be null")
    @Email(message = "User email is not valid")
    @Column(unique = true)
    private String email;
    
    @NotNull(message = "Username cannot be null")
    @Size(min = 3, message = "Username is too short")
    @Column(unique = true)
    private String username;
    
    @JsonIgnore
    private String password;
}
