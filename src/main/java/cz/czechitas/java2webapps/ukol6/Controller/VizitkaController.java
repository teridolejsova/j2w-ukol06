package cz.czechitas.java2webapps.ukol6.Controller;

import cz.czechitas.java2webapps.ukol6.Repository.VizitkaRepository;
import cz.czechitas.java2webapps.ukol6.entity.Vizitka;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class VizitkaController {
    private VizitkaRepository vizitkaRepository;

    public VizitkaController(VizitkaRepository vizitkaRepository) {
        this.vizitkaRepository = vizitkaRepository;
    }

    @InitBinder
    public void nullStringBinding(WebDataBinder binder) {

        //prázdné textové řetězce nahradit null hodnotou
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public ModelAndView seznam() {
        ModelAndView modelAndView = new ModelAndView("seznam");
        modelAndView.addObject("seznam", vizitkaRepository.findAll());
        return modelAndView;
    }

    @GetMapping("detail/{id}")
    public Object detail(@PathVariable int id) {
        ModelAndView detail = new ModelAndView("vizitka");
        Optional<Vizitka> vizitkaOptional = vizitkaRepository.findById(id);
        if (vizitkaOptional.isPresent()) {
            detail.addObject("vizitka", vizitkaOptional.get());
            return detail;
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nova")
    public ModelAndView nova() {
        ModelAndView nova = new ModelAndView("formular")
                .addObject("vizitka", new Vizitka())
                .addObject("nova", true);
        return nova;
    }

    @PostMapping("/nova")
    public String nova(@Valid @ModelAttribute("vizitka") Vizitka vizitka, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formular";
        }
        vizitkaRepository.save(vizitka);
        return "redirect:/";
    }

    @PostMapping("/smazat")
    public String smazat(int id) {
        vizitkaRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/upravit")
    public ModelAndView upravit(int id) {
        ModelAndView modelAndView = new ModelAndView("/formular")
                .addObject("vizitka", vizitkaRepository.findById(id).get())
                .addObject("nova", false);
        return modelAndView;
    }

    @PostMapping("/upravit")
    public String ulozitUpravy(@Valid @ModelAttribute("vizitka") Vizitka vizitka, BindingResult bindingResult){
            return nova(vizitka, bindingResult);
    }


}