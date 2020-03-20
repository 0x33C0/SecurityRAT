package org.appsec.securityrat.api.rest;

import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URISyntaxException;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.appsec.securityrat.api.dto.CollectionCategory;
import org.appsec.securityrat.api.dto.CollectionInstance;


@RestController
@RequestMapping("/api")
@Slf4j
public class CollectionCategoryInstanceResource
        extends AbstractResourceBase<Long, CollectionCategory> {

    public CollectionCategoryInstanceResource() {
        super("collectionCategoryInstance");
    }
    
    @RequestMapping(value = "/collectionCategoryInstances",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(
            @RequestBody CollectionCategory collectionCategory) throws URISyntaxException {
        return this.doCreate(collectionCategory);
    }

    @RequestMapping(value = "/collectionCategoryInstances",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(
            @RequestBody CollectionCategory collectionCategory)
            throws URISyntaxException {
        return this.doUpdate(collectionCategory);
    }

    @RequestMapping(value = "/collectionCategoryInstances",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        return this.doGetAll();
    }

    @RequestMapping(value = "/collectionCategoryInstances/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<CollectionInstance>> get(@PathVariable Long id) {
        // TODO [luis.felger@bosch.com]: Implement this.
        
        log.warn("Not implemented");
        return null;
    }

    @RequestMapping(value = "/collectionCategoryInstances/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return this.doDelete(id);
    }

    @RequestMapping(value = "/_search/collectionCategoryInstances/{query}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> search(@PathVariable String query) {
        return this.doSearch(query);
    }

    @Override
    protected URI getLocation(CollectionCategory dto) throws URISyntaxException {
        return new URI("/api/collectionCategoryInstances/" + dto.getId().get());
    }
}

