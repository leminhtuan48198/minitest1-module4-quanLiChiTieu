package com.codegym.cms.controller;

import com.codegym.cms.model.Spending;
import com.codegym.cms.model.SpendingForm;
import com.codegym.cms.service.ISpendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class SpendingController {

    @Autowired
    private ISpendingService spendingService;
    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping("/create-spending")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/spending/create");
        modelAndView.addObject("spendingForm", new SpendingForm());
        return modelAndView;
    }

    @PostMapping("/create-spending")
    public ModelAndView saveSpending(@ModelAttribute("spendingForm") SpendingForm spendingForm) {
        MultipartFile multipartFile = spendingForm.getPicture();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(spendingForm.getPicture().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Spending spending = new Spending(spendingForm.getId(), spendingForm.getName(),spendingForm.getPrice(),spendingForm.getDescription(),spendingForm.getCategory(), fileName);


        spendingService.save(spending);
        ModelAndView modelAndView = new ModelAndView("/spending/create");
        modelAndView.addObject("spendingForm", spendingForm);
        modelAndView.addObject("message", "New spending created successfully");
        return modelAndView;
    }
    @GetMapping("/spendings")
    public ModelAndView listSpendings() {
        List<Spending> spendings = spendingService.findAll();
        ModelAndView modelAndView = new ModelAndView("/spending/list");
        modelAndView.addObject("spendings", spendings);
        return modelAndView;
    }
    @GetMapping("/edit-spending/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Spending spending = spendingService.findById(id);
        if (spending != null) {
            SpendingForm spendingForm=new SpendingForm(spending.getId(),spending.getName(),spending.getPrice(),spending.getDescription(),spending.getCategory());
            ModelAndView modelAndView = new ModelAndView("/spending/edit");
            modelAndView.addObject("spendingForm", spendingForm);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-spending")
    public ModelAndView updateSpending(@ModelAttribute("spendingForm") SpendingForm spendingForm) {
        MultipartFile multipartFile = spendingForm.getPicture();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(spendingForm.getPicture().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Spending spending = new Spending(spendingForm.getId(), spendingForm.getName(),spendingForm.getPrice(),spendingForm.getDescription(),spendingForm.getCategory(), fileName);

        spendingService.save(spending);
        ModelAndView modelAndView = new ModelAndView("/spending/edit");
        modelAndView.addObject("spending", spending);
        modelAndView.addObject("message", "Spending updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete-spending/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Spending spending = spendingService.findById(id);
        if (spending != null) {
            ModelAndView modelAndView = new ModelAndView("/spending/delete");
            modelAndView.addObject("spending", spending);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-spending")
    public String deleteSpending(@ModelAttribute("spending") Spending spending) {
        spendingService.remove(spending.getId());
        return "redirect:spendings";
    }
}