package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    /* 1. Static */
    /* 내가 처음부터 끝까지 만든 html 파일만 단순히 연결해줌 */
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!"); // 모델에 매핑 Attribute를 선언하고
        return "hello"; // hello.html에 위 모델을 넘겨라 (data = hello!)
    }


    /* 2. MVC, Template */
    /* RequestParam을 통해 매핑이 들어오고 + 템플릿 html이 리턴되면
    ViewResolver를 Call해서 템플릿의 html을 동적으로 제작해줌 */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";  // Template이 HTML로 자동 변환해 브라우저에 넘김
    }


    /* 3. API - String */
    /* API는 ResponseBody가 있을 때 실행되며, View가 없고 리턴값을 그대로 반환해줌
    (1) String이 리턴되면 StringConverter를 통해 그대로 화면에 Data만 뿌려주고
    (2) 객체가 리턴되면 JsonConverter를 통해 객체의 멤버들을 Json으로 뿌려줌 */
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;  // 리턴을 그대로 넘겨줌 (html이 아님, data만 넘길 때 유용함)
    }

    /* 4. API - JSON */
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;  // 알아서 JSON으로 Class 객체의 멤버 반환
    }

    static class Hello {
        private String name;

        /* 자바빈 표준 방식 - Getter, Setter */
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
