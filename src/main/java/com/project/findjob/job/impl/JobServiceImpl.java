package com.project.findjob.job.impl;

import com.project.findjob.job.Job;
import com.project.findjob.job.JobService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    List<Job> jobs = new ArrayList<>();

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public String createJob(@RequestBody Job job) {
        jobs.add(job);
        return "Job added successfully";
    }

    @Override
    public Job getJobById(Long id) {
        return jobs.stream().filter(job -> job.getId().equals(id)).findFirst().orElseGet(() -> null);
    }
}
