package org.appsec.securityrat.api.rest;

import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.inject.Inject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.appsec.securityrat.api.SlideTemplateProvider;
import org.appsec.securityrat.api.dto.SlideTemplate;

@RestController
@RequestMapping("/api")
@Slf4j
public class SlideTemplateResource extends AbstractResourceBase<Long, SlideTemplate> {
    @Inject
    @Getter(AccessLevel.PROTECTED)
    private SlideTemplateProvider dtoProvider;
    
    public SlideTemplateResource() {
        super("slideTemplate");
    }
    
    @RequestMapping(value = "/slideTemplates",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SlideTemplate> create(@RequestBody SlideTemplate slideTemplate) throws URISyntaxException {
        return this.doCreate(slideTemplate);
    }

    @RequestMapping(value = "/slideTemplates",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SlideTemplate> update(@RequestBody SlideTemplate slideTemplate) throws URISyntaxException {
        return this.doUpdate(slideTemplate);
    }

    @RequestMapping(value = "/slideTemplates",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SlideTemplate> getAll() {
        return this.doGetAll();
    }

    @RequestMapping(value = "/slideTemplates/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SlideTemplate> get(@PathVariable Long id) {
        return this.doGet(id);
    }

    @RequestMapping(value = "/slideTemplates/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return this.doDelete(id);
    }

    @RequestMapping(value = "/_search/slideTemplates/{query}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SlideTemplate> search(@PathVariable String query) {
        return this.doSearch(query);
    }

    @Override
    protected URI getLocation(SlideTemplate dto) throws URISyntaxException {
        return new URI("/api/slideTemplates/" + dto.getId().get());
    }
}