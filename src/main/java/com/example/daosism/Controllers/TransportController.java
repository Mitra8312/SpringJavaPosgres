package com.example.daosism.Controllers;

import com.example.daosism.CRUD.UniversalService;
import com.example.daosism.Models.Transport;
import com.example.daosism.Repositories.TransportRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TransportController {

    private UniversalService us;
    private final TransportRepo tr;
    public List<Transport> transp = new ArrayList<Transport>();

    public TransportController(UniversalService us, TransportRepo tr) {
        this.us = us;
        this.tr = tr;
    }

    @GetMapping("/Transports")
    public String Transports(Model model){
        transp = us.getAll(Transport.class);
        model.addAttribute("transp", transp);
        return "Transports";
    }

    @GetMapping("/deleteTransport/{transpId}")
    public String DeleteTransport(@PathVariable("transpId") int id) {

        us.delete(Transport.class, id);
        return "redirect:/Transports";
    }

    @GetMapping("/UpdateTransport/{transpId}")
    public String UpdateLocation(@PathVariable("transpId") Integer id, Model model) {
        for (int i = 0; i < transp.size(); i++) {
            if (transp.get(i).getId() == id) {
                model.addAttribute("name", transp.get(i).getName());
                model.addAttribute("id", transp.get(i).getId());
                model.addAttribute("number", transp.get(i).getNumber());
                model.addAttribute("description", transp.get(i).getDescription());
                model.addAttribute("driver", transp.get(i).getDriver());
                model.addAttribute("location", transp.get(i).getLocation());
            }
        }
        return "Transport";
    }

    @GetMapping("/AddTransport")
    public String AddTransport(@ModelAttribute("name") String name, @ModelAttribute("number") String number, @ModelAttribute("description") String description, @ModelAttribute("driver") String driver, @ModelAttribute("location") String location){
        try {

            us.create(new Transport(name, number, description, driver, location));

        } catch (Exception ignored){

        }
        return "redirect:/Transports";
    }

    @PostMapping("/updateTransport")
    public String UpdateTransport(@ModelAttribute("name") String name, @ModelAttribute("number") String number, @ModelAttribute("description") String description, @ModelAttribute("driver") String driver, @ModelAttribute("location") String location, @ModelAttribute("id") Integer id){

        us.update(new Transport(name, number, description, driver, location, id));
        return "redirect:/Transports";
    }

    @GetMapping("/GetTransport")
    public String UpdateLocation(@ModelAttribute("numberTr") String numberTr, Model model) {
        Transport transport;
        transport = tr.findTransportByNumber(numberTr.trim());

        model.addAttribute("name", transport.getName());
        model.addAttribute("id", transport.getId());
        model.addAttribute("number", transport.getNumber());
        model.addAttribute("description", transport.getDescription());
        model.addAttribute("driver", transport.getDriver());
        model.addAttribute("location", transport.getLocation());

        return "Transport";
    }

    @GetMapping("/goI")
    public String Index() {

        return "Index";
    }
}
