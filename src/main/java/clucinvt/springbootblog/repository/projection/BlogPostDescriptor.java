/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.repository.projection;

import java.util.Date;

/**
 *
 * @author Cam Luc
 */
public interface BlogPostDescriptor {
    
    int getId();
    String getTitle();
    String getAuthorName();
    Date getCreateDate();
    Date getPublishDate();
    int getViewCount();
}
