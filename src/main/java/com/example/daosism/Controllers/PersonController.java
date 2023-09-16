package com.example.daosism.Controllers;

import com.example.daosism.CRUD.UniversalService;
import com.example.daosism.Models.Person;
import com.example.daosism.Repositories.PersonRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {

    private UniversalService us;
    private final PersonRepo pr;
    public List<Person> perses = new ArrayList<>();

    public PersonController(UniversalService us, PersonRepo pr) {
        this.us = us;
        this.pr = pr;
    }

    @GetMapping("/People")
    public String People(Model model){
        perses = us.getAll(Person.class);

        model.addAttribute("perses", perses);
        return "People";
    }

    @GetMapping("/deletePerson/{personId}")
    public String DeletePerson(@PathVariable("personId") int id) {

        us.delete(Person.class, id);
        return "redirect:/People";
    }

    @GetMapping("/UpdatePerson/{personId}")
    public String UpdatePerson(@PathVariable("personId") Integer id, Model model) {
        for (int i = 0; i < perses.size(); i++) {
            if (perses.get(i).getId() == id) {
                model.addAttribute("name1", perses.get(i).getName());
                model.addAttribute("id1", perses.get(i).getId());
                model.addAttribute("secondName1", perses.get(i).getSecondName());
                model.addAttribute("age1", perses.get(i).getAge());
                model.addAttribute("family1", perses.get(i).getFamily());
            }
        }
        return "Person";
    }

    @GetMapping("/AddPeople")
    public String AddPerson(@ModelAttribute("name") String name, @ModelAttribute("secondName") String secondname, @ModelAttribute("age") Integer age, @ModelAttribute("family") String family){

        us.create(new Person(name, secondname, age, family));
        return "redirect:/People";
    }

    @PostMapping("/updatePerson")
    public String UpdatePerson(@ModelAttribute("name1") String name, @ModelAttribute("secondName1") String secondname, @ModelAttribute("age1") Integer age, @ModelAttribute("family1") String family, @ModelAttribute("id1") Integer id){

        us.update(new Person(name, secondname, age, family, id));
        return "redirect:/People";
    }

    @GetMapping("/GetPerson")
    public String GetPerson(@ModelAttribute("personId") Integer id, Model model) {
        Person person;
        person = pr.getPersonById(id);

        model.addAttribute("name1", person.getName());
        model.addAttribute("id1", person.getId());
        model.addAttribute("secondName1", person.getSecondName());
        model.addAttribute("age1", person.getAge());
        model.addAttribute("family1", person.getFamily());

        return "Person";
    }

    @GetMapping("/goInde")
    public String Index() {

        return "Index";
    }
}
