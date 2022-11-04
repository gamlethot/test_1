package ua.shamray.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.shamray.dao.PersonDAO;
import ua.shamray.models.Person;

@Controller
@ComponentScan("ua.shamray")
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/show")
    public String show(Model model){
        model.addAttribute("people", personDAO.getAll());
        return "people/show";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.getById(id));
        return "people/person";
    }

    @GetMapping("/create-form")
    public ModelAndView createForm(ModelAndView modelAndView){
        modelAndView.setViewName("people/create-form");
        modelAndView.addObject("person", new Person());
        return modelAndView;
    }

    @PostMapping()
    public ModelAndView createPerson(@ModelAttribute("person") Person person, ModelAndView modelAndView){
        modelAndView.setViewName("redirect:people/show");
        personDAO.create(person);
        return modelAndView;
    }


}
