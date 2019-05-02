package dihtiar.sasha.controller;


import dihtiar.sasha.model.MyProperties;
import dihtiar.sasha.service.DictionaryService;
import dihtiar.sasha.service.MyCurrencyService;
import dihtiar.sasha.service.MyPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PropertiesController {

    @Autowired
    MyCurrencyService myCurrencyService;

    @Autowired
    MyPropertiesService myPropertiesService;

    @Autowired
    DictionaryService dictionaryService;

    @GetMapping("/properties/get")
    public String showProperties(Model model) {
        model.addAttribute("shpr", myPropertiesService.showProps());
        return "showproperties";
    }

    @GetMapping(value = "/properties")
    public String createProperties(Model model) {
        model.addAttribute("curr", myCurrencyService.getAll());
        return "prop";
    }

    @PostMapping(value = "/properties")
    public String createProperties(@RequestParam("propvalue") String propvalue,
                                   @RequestParam("coftime") String cofTime,
                                   @RequestParam("cofplace") String cofPlsce,
                                   @RequestParam("cost") String cost) {
        MyProperties properties0 = myPropertiesService.findProps("default.cost");
        if (properties0 == null) {
            properties0 = new MyProperties();
            properties0.setNamek("default.cost");
        }
        properties0.setValue(cost);
        dictionaryService.addProps(properties0);

        MyProperties properties = myPropertiesService.findProps("default.currency");
        if (properties == null) {
            properties = new MyProperties();
            properties.setNamek("default.currency");
        }
        properties.setValue(propvalue);
        dictionaryService.addProps(properties);

        MyProperties properties1 = myPropertiesService.findProps("default.coffortime");
        if (properties1 == null) {
            properties1 = new MyProperties();
            properties1.setNamek("default.coffortime");
        }
        properties1.setValue(cofTime);
        dictionaryService.addProps(properties1);

        MyProperties properties2 = myPropertiesService.findProps("default.cofforplace");
        if (properties2 == null) {
            properties2 = new MyProperties();
            properties2.setNamek("default.cofforplace");
        }
        properties2.setValue(cofPlsce);
        dictionaryService.addProps(properties2);
        return "redirect:/properties/get";
    }
}
