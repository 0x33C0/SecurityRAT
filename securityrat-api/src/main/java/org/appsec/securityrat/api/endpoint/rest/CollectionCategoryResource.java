package org.appsec.securityrat.api.endpoint.rest;

import java.util.List;
import java.util.Set;
import org.appsec.securityrat.api.dto.rest.CollectionCategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CollectionCategoryResource
        extends SimpleResource<Long, CollectionCategoryDto> {
    
    public CollectionCategoryResource() {
        super(CollectionCategoryDto.class);
    }

    @Override
    @PostMapping("/collectionCategorys")
    protected ResponseEntity<CollectionCategoryDto> create(
            @RequestBody CollectionCategoryDto dto) {
        return super.create(dto);
    }

    @Override
    @PutMapping("/collectionCategorys")
    protected ResponseEntity<CollectionCategoryDto> update(
            @RequestBody CollectionCategoryDto dto) {
        return super.update(dto);
    }

    @Override
    @GetMapping("/collectionCategorys")
    protected ResponseEntity<Set<CollectionCategoryDto>> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/collectionCategorys/{id}")
    protected ResponseEntity<CollectionCategoryDto> get(@PathVariable Long id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/collectionCategorys/{id}")
    protected ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @Override
    @GetMapping("/_search/collectionCategorys/{query}")
    protected ResponseEntity<List<CollectionCategoryDto>> search(
            @PathVariable String query) {
        return super.search(query);
    }
}
