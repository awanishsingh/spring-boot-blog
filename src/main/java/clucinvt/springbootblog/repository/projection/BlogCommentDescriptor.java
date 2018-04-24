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
public interface BlogCommentDescriptor {
    
    int getId();
    int getPostId();
    String getCommenterName();
    String getTitle();
    Date getCreateDate();
    Date getApproveDate();
}
