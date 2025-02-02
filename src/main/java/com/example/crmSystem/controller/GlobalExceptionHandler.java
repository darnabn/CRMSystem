package com.example.crmSystem.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        return "redirect:/requests"; // Перенаправление на главную страницу
    }

    @ExceptionHandler(SQLException.class)
    public String handleSQLException(SQLException ex, Model model) {
        model.addAttribute("errorMessage", "Ошибка базы данных: " + ex.getMessage());
        return "error"; // Перенаправление на шаблон error.html
    }
}

