package org.timespace.category;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.timespace.config.Navigation;
import org.timespace.config.Section;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
@Navigation(Section.CATEGORY)
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public String categories(Pageable pageable, Model model) {
        model.addAttribute("categories", categoryService.findAll(pageable));
        return "category/list";
    }

    @GetMapping("/new")
    public String newCategory(@ModelAttribute CategoryData categoryData) {
        return "category/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("categoryData", categoryService.findOne(id));
        return "category/edit";
    }

    @PostMapping
    public String createCategory(@ModelAttribute @Valid CategoryData categoryData, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "category/new";
        }
        categoryService.createCategory(new Category(categoryData.getId(), categoryData.getName(), LocalDateTime.now()));
        return "redirect:/categories";
    }

    @PostMapping("/{id}/edit")
    public String modifyCategory(@PathVariable Long id, @ModelAttribute @Valid CategoryData categoryData, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "category/edit";
        }
        categoryService.updateCategory(new Category(id, categoryData.getName()));
        return "redirect:/categories";
    }

    @PostMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}