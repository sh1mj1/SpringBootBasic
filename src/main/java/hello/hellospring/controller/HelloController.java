package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // web 어플리케이션에서 /hello 라고 들어오면 이 메서드를 호출해줌.
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring-boot!!");
        return "hello";
    }

//    @GetMapping("hello-mvc")
//    public String helloMvc(@RequestParam("name") String name, Model model) {
//        model.addAttribute("name", name);
//        return "hello-template";
//    }

    @GetMapping("hello-mvc")
    // 외부에서 parameter 을 받을 것임.
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name); // 앞이 key, 뒤가 name
        return "hello-template";
    }

    /*
    http 에서 header 부와 body 부가 있음.
    body 부에 이 데이터를 직접 넣어준다는 뜻 'ResponseBody'
     */
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name; // "hello springboot" 요청한 string 이 그대로 내려간다.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);

        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
