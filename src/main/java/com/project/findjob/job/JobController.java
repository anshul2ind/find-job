package com.project.findjob.job;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;
    private Long nextId = 1L;


    @GetMapping()
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping()
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        job.setId(nextId++);
        jobService.createJob(job);
        return ResponseEntity.ok("Job added successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if(job != null) {
            return ResponseEntity.ok(job);
        }
        return ResponseEntity.notFound().build();

    }

}
