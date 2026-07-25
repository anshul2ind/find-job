package com.project.findjob.job;

import java.util.List;

public interface JobService {
    public List<Job> findAll();
    public String createJob(Job job);
    public Job getJobById(Long id);
}
