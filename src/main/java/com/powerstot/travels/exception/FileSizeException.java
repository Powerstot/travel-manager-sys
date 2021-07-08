package com.powerstot.travels.exception;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class FileSizeException {
    @ExceptionHandler(FileSizeLimitExceededException.class)
    public String handle(Exception e, Model model) {
        model.addAttribute("msg", e.getMessage());
        return "error";
    }
}
