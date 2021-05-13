package com.devsuperior.dscatalog.resources;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.resources.events.CreatedResourceEvent;
import com.devsuperior.dscatalog.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryResource {

    private final CategoryService categoryService;

    private final ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> list = categoryService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        CategoryDTO dto = categoryService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO create(@RequestBody CategoryDTO categoryDTO, HttpServletResponse response) {
        categoryDTO = categoryService.create(categoryDTO);
        publisher.publishEvent(new CreatedResourceEvent(this, categoryDTO.getId(), response));
        return categoryDTO;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO update(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return categoryService.update(id, categoryDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
