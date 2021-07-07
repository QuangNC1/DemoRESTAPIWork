package com.example.demo.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Work;
import com.example.demo.service.WorkService;

@RestController
@RequestMapping("/api")
public class RestApiController {
    public static Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    WorkService workService;

    @RequestMapping(value = "/work/page={page}", method = RequestMethod.GET)
    public ResponseEntity<List<Work>> listAllWork(@PathVariable("page") int page){
        Sort sort = Sort.by("status").ascending();
        int recordOnPage = 5;
        int startRecord = ((page-1) * recordOnPage);
        int endPage = startRecord + recordOnPage;
        Pageable pageable = PageRequest.of(startRecord, endPage, sort);

        List<Work> pageList = null;
        Page<Work> pageWork= workService.findAll(pageable);
        if(pageWork != null && pageWork.hasContent()) {
            pageList = pageWork.getContent();
        }
        
        if(pageList.isEmpty()) {
            return new ResponseEntity<List<Work>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Work>>(pageList, HttpStatus.OK);
    }

    @RequestMapping(value = "/work/{name}", method = RequestMethod.GET)
    public Work findWork(@PathVariable("name") String name) {
        Work work= workService.getOne(name);
        if(work == null) {
            ResponseEntity.notFound().build();
        }
        return work;
    }

    @RequestMapping(value = "/work/", method = RequestMethod.POST)
    public Work saveWork(@Valid @RequestBody Work work) {
        return workService.save(work);
    }
    @RequestMapping(value = "/work/", method = RequestMethod.PUT)
    public ResponseEntity<Work> updateWork(@Valid @RequestBody Work workForm) {

        Work work = workService.getOne(workForm.getWork_name());
        if(work == null) {
            return ResponseEntity.notFound().build();
        }
        work.setStarting_date(workForm.getStarting_date());
        work.setEnding_date(workForm.getEnding_date());
        work.setStatus(workForm.getStatus());

        Work updatedWork = workService.save(work);
        return ResponseEntity.ok(updatedWork);
    }

    @RequestMapping(value = "/work/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<Work> deleteWork(@PathVariable(value = "name") String name) {
        Work work = workService.getOne(name);
        if(work == null) {
            return ResponseEntity.notFound().build();
        }

        workService.delete(work);
        return ResponseEntity.ok().build();
    }
}