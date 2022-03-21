package vn.techmaster.lesson1.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.lesson1.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("")
public class HomeController {
    private  ArrayList<Student> listStudent = new ArrayList<Student>() {{
        add(new Student(1,"Duc",22));
        add(new Student(2,"Doan",22));
        add(new Student(3,"Son",22));
    }};

    @GetMapping("/random")
    @ResponseBody
    public String getRandom8Character(){
         char[] arrCharacter = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
                                ,'0','1','2','3','4','5','6','7','8','9',
                 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
         StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            Random random = new Random();
            int randomNumber = random.nextInt((arrCharacter.length-1) + 1 - 0) + 0;
            stringBuilder.append(arrCharacter[randomNumber]);
        }
         return stringBuilder+"";
    }

    @GetMapping("/quote")
    @ResponseBody
    public String getRandomQuote(){
        List<String>  listQuote = new ArrayList<>();
        listQuote.add("Kiến tha lâu đầy tổ");
        listQuote.add("Có công mài sắt, có ngày nên kim");
        listQuote.add("Không thầy đố mày làm nên");
        listQuote.add("Học thầy không tày học bạn");
        Random random = new Random();
        int randomNumber = random.nextInt((listQuote.size() -1) + 1 - 0) + 0;
        return listQuote.get(randomNumber);
    }

    @PostMapping("/bmi")
    @ResponseBody
    public Double caculatorBMI(@RequestParam Double weight, @RequestParam Double height){
        Double result =  weight / (height * height);
        return  result;
    }
    @GetMapping("/listStudent")
    @ResponseBody
    public List<Student>  getStudentList(){
        return listStudent;
    }
    @PostMapping("/addStudent")
    @ResponseBody
    public Student addStudent(@RequestBody Student student){
        listStudent.add(student);
        return student;
    }
}
