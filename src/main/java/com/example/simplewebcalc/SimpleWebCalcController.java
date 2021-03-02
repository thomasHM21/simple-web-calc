package com.example.simplewebcalc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SimpleWebCalcController {

    //Level 1
    @GetMapping("/")
    public String level1(Model model, @RequestParam int number1, @RequestParam int number2) {
        int sum = number1 + number2;
        model.addAttribute("number1", number1);
        model.addAttribute("number2", number2);
        model.addAttribute("sum", sum);
        return "level1";
    }

    //Level 2
    @GetMapping("/add")
    public String level2() {
        return "add";
    }

    @PostMapping("/add")
    public String level2post(HttpSession session, @RequestParam Integer number) {
        Integer total = (Integer) session.getAttribute("total");
        List<Integer> numbers = (List<Integer>)session.getAttribute("numbers");
        if (total == null) {
            total = number;
            numbers = new ArrayList<>();

        } else total = total + number;
        numbers.add(number);
        session.setAttribute("total", total);
        session.setAttribute("numbers",numbers);

        return "add";
    }

    @PostMapping("/reset")
    public String resetCalc(HttpSession session){
        session.removeAttribute("total");
        return "redirect:/add";
    }
}
