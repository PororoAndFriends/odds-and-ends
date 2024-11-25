package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello~!");

        return "hello";
    }

    @GetMapping("hello-mvc")
    public String hello(@RequestParam(value = "name", defaultValue = "Pororo") String name,
                        @RequestParam("age") String age, Model model){
        model.addAttribute("name", name);
        model.addAttribute("age", age);

        return "hello-templete";
    }

    // API 방식
    // request body : https body에 값을 넣어 전달-
    // 데이터만 주고받을때 편함
    // string을 return하면 string으로 넘겨줌
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);

        return "name : " + name;
    }

    // API방식
    // class를 return하면 json 형식(hash와 유사)으로 넘겨줌
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name,@RequestParam("age") int age, Model model){
        Hello hello = new Hello();
        hello.setName(name);
        hello.setAge(age);

        return hello;
    }

    static class Hello{
        private String name;

        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
