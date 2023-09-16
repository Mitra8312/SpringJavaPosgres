package com.example.daosism.Controllers;

import com.example.daosism.Models.Cell;
import com.example.daosism.Repositories.CellRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CellController {
    private final CellRepo us;
    private Cell updateableCell;
    public List<Cell> cells = new ArrayList<>();

    @Autowired
    public CellController(CellRepo us){
        updateableCell = new Cell();
        this.us = us;
    }

    @GetMapping("/Cells")
    public String Cells(Model model){
        cells = us.findAll();

        model.addAttribute("cells", cells);
        return "Cells";
    }

    @GetMapping("/deleteCell/{cellId}")
    public String DeleteCell(@PathVariable("cellId") int id) {

        us.deleteById(id);
        return "redirect:/Cells";
    }

    @GetMapping("/UpdateCell/{cellId}")
    public String UpdateCell(@PathVariable("cellId") Integer id, Model model) {
        for (Cell cell : cells) {
            if (cell.getId() == id) {
                updateableCell = cell;
                model.addAttribute("name", cell.getName());
                model.addAttribute("id", cell.getId());
                model.addAttribute("level", cell.getLevel());
                model.addAttribute("hash", cell.getHash());
                model.addAttribute("loc", cell.getLoc());
                model.addAttribute("product", cell.getProduct());
            }
        }
        return "Cell";
    }

    @PostMapping("/AddCell")
    public String AddCell(@ModelAttribute("name") String name, @ModelAttribute("level") Integer level, @ModelAttribute("hash") String hash, @ModelAttribute("loc") String loc, @ModelAttribute("product") String product){

        us.save(new Cell(name, level, hash, loc, product));
        return "redirect:/Cells";
    }

    @PostMapping("/updateCell")
    public String UpdateCell(@ModelAttribute("name") String name, @ModelAttribute("level") Integer level, @ModelAttribute("hash") String hash, @ModelAttribute("loc") String loc, @ModelAttribute("product") String product, @ModelAttribute("id") Integer id){

        us.delete(updateableCell);
        us.save(new Cell(name, level, hash, loc, product, id));
        return "redirect:/Cells";
    }

    @GetMapping("/GetCell")
    public String GetByProduct(@ModelAttribute("article") String art, Model model) {

        updateableCell = us.findCellByProduct(art.trim());

        model.addAttribute("name", updateableCell.getName());
        model.addAttribute("id", updateableCell.getId());
        model.addAttribute("level", updateableCell.getLevel());
        model.addAttribute("hash", updateableCell.getHash());
        model.addAttribute("loc", updateableCell.getLoc());
        model.addAttribute("product", updateableCell.getProduct());

        return "Cell";
    }

    @GetMapping("/goInd")
    public String Index() {

        return "Index";
    }
}
