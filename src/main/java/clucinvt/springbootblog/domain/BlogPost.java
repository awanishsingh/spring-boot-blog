/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
public class BlogPost {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @NotNull(message = "Post author cannot be null")
    private User author;
    
    @NotNull(message = "Post title cannot be null")
    @Size(min = 3, message = "Post title is too short")
    private String title;
    
    @NotNull(message = "Post body cannot be null")
    @Size(min = 10, message = "Post body is too short")
    @Column(columnDefinition = "TEXT")
    private String body;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;
    
    @ElementCollection
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "DATETIME")
    private List<Date> postViews;
}
