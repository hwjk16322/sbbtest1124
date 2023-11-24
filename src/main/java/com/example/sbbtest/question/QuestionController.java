package com.example.sbbtest.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/article/list")
    public String list(Model model) {
        List<Question> questionList = questionService.getlist();
        model.addAttribute("question", questionList);
        return "article_list";
    }

    @GetMapping("/article/create")
    public String create() {
        return "article_form";
    }

    @PostMapping("/article/create")
    public String create(@RequestParam(name = "subject") String subject, @RequestParam(name = "content") String content) {
        this.questionService.create(subject, content);
        return "redirect:/article/list";
    }

    @GetMapping("/article/detail/{id}")
    public String detail(Model model, @PathVariable(name = "id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "article_detail";
    }

}
