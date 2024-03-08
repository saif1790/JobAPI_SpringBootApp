package com.example.FirstSpringBoot.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/jobs")//at class level it is used to set base url for particular controller
public class JobController
{
    private final JobServices jobService;

    public JobController(JobServices jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    /*@GetMapping("/jobs")*/
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAllJob());
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job Created Successfully!",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findJobById(@PathVariable Integer id){
        Job job = jobService.findJobById(id);
        if(job != null)
            return new ResponseEntity<>(job,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    /*@DeleteMapping("jobs/{id}")*/
    public ResponseEntity<String> deleteJobById(@PathVariable Integer id)
    {
        String deleteMsg = jobService.deleteJobById(id);
        if(deleteMsg.equals("Job Deleted"))
        return new ResponseEntity<>("Job Deleted",HttpStatus.OK);
        return new ResponseEntity<>("Job Id Not found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("jobs/{id}")
   /* @RequestMapping(value = "/{id}", method = RequestMethod.PUT)*///at method level,it define url that this method supposed to be mapped too
    public ResponseEntity<String> updateJobById(@RequestBody Job updatedJob, @PathVariable Integer id)
    {
        String updateMsg = jobService.updateJobById(updatedJob,id);
        if(updateMsg.contains("job has been updated"))
            return new ResponseEntity<>("Job Updated",HttpStatus.OK);
        return new ResponseEntity<>("Job Id Not found",HttpStatus.NOT_FOUND);
    }

}

/*@RestController
@RequestMapping--It use on both method and class level
@PathVariable
@GetMapping
@PostMapping
@DeleteMapping
@PutMapping
@Service*/

