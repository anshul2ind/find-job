package com.project.findjob.job.impl;

import com.project.findjob.job.Job;
import com.project.findjob.job.JobService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    List<Job> jobs = new ArrayList<>();

    private Job findJobById(Long id) {
        return jobs.stream().filter(job -> job.getId().equals(id)).findFirst().orElseGet(() -> null);

    }

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
        return findJobById(id);
    }

    @Override
    public Job deleteJobById(Long id) {

        Job jobToDelete =  findJobById(id);
        if(jobToDelete != null) {
            jobs.remove(jobToDelete);
        }
        return jobToDelete;
    }

    @Override
    public Job updateJobById(Long id, Job updateJob) {
        Job deletedJob = deleteJobById(id);
        if(deletedJob != null) {
            updateJob.setId(deletedJob.getId());
            jobs.add(updateJob);
        }

        return updateJob;
    }
}
