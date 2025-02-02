package com.example.crmSystem.controller;

import com.example.crmSystem.entity.ApplicationRequest;
import com.example.crmSystem.service.ApplicationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/requests")
@RequiredArgsConstructor
public class ApplicationRequestController {

    private final ApplicationRequestService service;

    @GetMapping
    public String getAllRequests(Model model) {
        List<ApplicationRequest> requests = service.getAllRequests();
        model.addAttribute("requests", requests);
        model.addAttribute("pageTitle", "Все заявки");
        return "requests"; // вернется шаблон templates/requests.html
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("requestForm", new ApplicationRequest());
        model.addAttribute("pageTitle", "Добавить заявку");
        return "request-form";
    }

    @PostMapping
    public String createRequest(@ModelAttribute("requestForm") ApplicationRequest request) {
        request.setHandled(false); // по умолчанию необработано
        service.createRequest(request);
        return "redirect:/requests";
    }

    @GetMapping("/{id}")
    public String getRequestDetails(@PathVariable Long id, Model model) {
        ApplicationRequest req = service.getRequestById(id);
        model.addAttribute("request", req);
        model.addAttribute("pageTitle", "Детали заявки");
        return "request-details";
    }

    @GetMapping("/{id}/edit")
    public String editRequest(@PathVariable Long id, Model model) {
        ApplicationRequest req = service.getRequestById(id);
        model.addAttribute("requestForm", req);
        model.addAttribute("pageTitle", "Редактировать заявку");
        return "request-form"; // используем ту же форму, только с уже заполненными данными
    }

    @PostMapping("/{id}/update")
    public String updateRequest(@PathVariable Long id,
                                @ModelAttribute("requestForm") ApplicationRequest form) {
        form.setId(id);
        service.updateRequest(form);
        return "redirect:/requests";
    }

    @PostMapping("/{id}/delete")
    public String deleteRequest(@PathVariable Long id) {
        service.deleteRequest(id);
        return "redirect:/requests";
    }

}
