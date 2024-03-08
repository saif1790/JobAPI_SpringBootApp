package com.example.FirstSpringBoot.job;

import java.util.List;

public interface JobServices
{
   List<Job> findAllJob();

   String createJob(Job job);

   Job findJobById(Integer id);

   String deleteJobById(Integer id);

   String updateJobById(Job job,Integer Id);
}
