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
import org.appsec.securityrat.api.ReqCategoryProvider;
import org.appsec.securityrat.api.dto.ReqCategory;


@RestController
@RequestMapping("/api")
@Slf4j
public class ReqCategoryResource extends AbstractResourceBase<Long, ReqCategory> {
    @Inject
    @Getter(AccessLevel.PROTECTED)
    private ReqCategoryProvider dtoProvider;
    
    public ReqCategoryResource() {
        super("eqCategory");
    }
    
    @RequestMapping(value = "/reqCategorys",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReqCategory> create(@RequestBody ReqCategory reqCategory) throws URISyntaxException {
        return this.doCreate(reqCategory);
    }

    @RequestMapping(value = "/reqCategorys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReqCategory> update(@RequestBody ReqCategory reqCategory) throws URISyntaxException {
        return this.doUpdate(reqCategory);
    }

    @RequestMapping(value = "/reqCategorys",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReqCategory> getAll() {
        return this.doGetAll();
    }

    @RequestMapping(value = "/reqCategorys/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReqCategory> get(@PathVariable Long id) {
        return this.doGet(id);
    }

    @RequestMapping(value = "/reqCategorys/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return this.doDelete(id);
    }

    @RequestMapping(value = "/_search/reqCategorys/{query}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReqCategory> search(@PathVariable String query) {
        return this.doSearch(query);
    }

    @Override
    protected URI getLocation(ReqCategory dto) throws URISyntaxException {
        return new URI("/api/reqCategorys/" + dto.getId().get());
    }
}