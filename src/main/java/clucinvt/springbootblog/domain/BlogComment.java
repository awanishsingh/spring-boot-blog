/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Cam Luc
 */
@Entity
@Data
public class BlogComment {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    private BlogPost blogPost;
    
    @NotNull(message = "Commenter nickname cannot be null")
    @Size(min = 3, message = "Commenter nickname is too short")
    private String commenterName;
    
    @NotNull(message = "Comment title cannot be null")
    @Size(min = 3, message = "Comment title is too short")
    private String title;
    
    @NotNull(message = "Comment body cannot be null")
    @Size(min = 10, message = "Comment body is too short")
    @Column(columnDefinition = "TEXT")
    private String body;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date approveDate;
}
