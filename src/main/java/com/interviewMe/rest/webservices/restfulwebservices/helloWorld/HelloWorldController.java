package com.interviewMe.rest.webservices.restfulwebservices.helloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-annotation")
    public String helloWorldWithAnnotation() {
        return "Hello World Annotation";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldWithBean() {
        return new HelloWorldBean("Hello World Bean");
    }

    @GetMapping(path = "/hello-world-bean/{name}")
    public HelloWorldBean helloWorldWithPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(/*@RequestHeader(name = "Accept-Language", required = false) Locale locale*/) {
//        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
        return messageSource.getMessage(
                "good.morning.message", null, "Default Message", LocaleContextHolder.getLocale());
    }


}
