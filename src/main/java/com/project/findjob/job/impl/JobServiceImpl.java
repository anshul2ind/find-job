package com.project.findjob.job.impl;

import com.project.findjob.job.Job;
import com.project.findjob.job.JobService;
import org.springframework.stereotype.Service;

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
    public String createJob(Job job) {
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
    public boolean updateJobById(Long id, Job updateJob) {
        Job job = findJobById(id);
        if(job != null) {
            job.setTitle(updateJob.getTitle());
            job.setDescription(updateJob.getDescription());
            job.setLocation(updateJob.getLocation());
            job.setMinSalary(updateJob.getMinSalary());
            job.setMaxSalary(updateJob.getMaxSalary());
            return true;
        }

        return false;
    }
}
