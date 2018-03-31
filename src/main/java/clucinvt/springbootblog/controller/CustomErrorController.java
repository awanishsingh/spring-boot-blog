/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clucinvt.springbootblog.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Cam Luc
 */
@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH, method = {RequestMethod.GET})
    public String error(HttpServletRequest httpRequest, Model model) {
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                errorMsg = " Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Unauthorized";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Resource not found";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Internal Server Error";
                break;
            }
        }
        model.addAttribute("errorMsg", errorMsg);
        return "error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }

}
