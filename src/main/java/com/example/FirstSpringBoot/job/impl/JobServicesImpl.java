package com.example.FirstSpringBoot.job.impl;

import com.example.FirstSpringBoot.job.Job;
import com.example.FirstSpringBoot.job.JobRepository;
import com.example.FirstSpringBoot.job.JobServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServicesImpl implements JobServices
{
   private JobRepository jobRepository;

    public JobServicesImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> findAllJob(){
        return jobRepository.findAll();
    }

    public String createJob(Job job){
        jobRepository.save(job);
        return "Job Created Successfully!";
    }

    public Job findJobById(Integer id)
    {
        return  jobRepository.findById(id).orElse(null);
    }

    public String deleteJobById(Integer id)
    {
        try {
           Job job =(Job) jobRepository.findById(id).orElse(null);
           if(job != null) {
               jobRepository.deleteById(id);
               return "Job Deleted";
           }else {
               return "Job Id Not found";
           }
        }catch (Exception e){
            return "Job Id Not found";
        }
    }

    public String updateJobById(Job updatedJob , Integer id)
    {
        Optional<Job> optionalJob = jobRepository.findById(id);

        if (optionalJob.isPresent()) {
            Job existingJob = optionalJob.get();
            System.out.println("Hello existing job");
            existingJob.setTitle(updatedJob.getTitle());
            System.out.println(existingJob.getTitle());
            existingJob.setDescription(updatedJob.getDescription());
            existingJob.setLocation(updatedJob.getLocation());
            existingJob.setMinSalary(updatedJob.getMinSalary());
            existingJob.setMaxSalary(updatedJob.getMaxSalary());
            jobRepository.save(existingJob);
            return  "ID with "+ id + "job has been updated";
        }
        return "Not Updated";
    }
}

/*============================= without JPA*===================/
/*
package com.example.FirstSpringBoot.job.impl;

        import com.example.FirstSpringBoot.job.Job;
        import com.example.FirstSpringBoot.job.JobServices;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;
        import org.springframework.stereotype.Service;

        import java.util.ArrayList;
        import java.util.List;

@Service

public class JobServicesImpl implements JobServices
{
    List<Job> jobs = new ArrayList<>();


    public List<Job> findAllJob(){
        return jobs;
    }

    public String createJob(Job job){
        System.out.println("Service Impl");
        jobs.add(job);
        return "Job Created Successfully!";
    }

    public Job findJobById(Integer id)
    {
        for(Job job : jobs)
        {
            if(job.getId().equals(id))
                return job;
        }
        return null;
    }

    public String deleteJobById(Integer id)
    {System.out.println(id);
        for(Job job : jobs)
        {
            if(job != null) {
                if (job.getId().equals(id)) {
                    jobs.remove(job);
                    return "Job Deleted";
                }
            }
        }
        return "Job Id Not found";
    }

    public String updateJobById(Job updatedJob , Integer id)
    {
        for(Job existingJob : jobs)
        {
            if (existingJob.getId().equals(id)) {
                System.out.println("Hello existing job");
                existingJob.setTitle(updatedJob.getTitle());
                System.out.println(existingJob.getTitle());
                existingJob.setDescription(updatedJob.getDescription());
                existingJob.setLocation(updatedJob.getLocation());
                existingJob.setMinSalary(updatedJob.getMinSalary());
                existingJob.setMaxSalary(updatedJob.getMaxSalary());
                return  "ID with "+ id + "job has been updated";
            }
        }
        return "Not Updated";
    }
}
*/
