package com.waa.amp.service;

import com.waa.amp.dto.JobReq;
import com.waa.amp.entity.Job;
import com.waa.amp.entity.Tag;
import com.waa.amp.repository.JobRepository;
import com.waa.amp.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final JobRepository jobRepository;
    private final TagRepository tagRepository;

    public Job postJob(JobReq jobReq) {

        List<Tag> tags = new ArrayList<>();

        for (String it : jobReq.tag()) {
            var t = tagRepository.findByTags(it);
            tags.add(t);
        }

        var job = new Job(null,
                jobReq.description(),
                tags,
                jobReq.state(),
                jobReq.city(),
                jobReq.companyName(),
                null // TODO get logged in user
        );

        return jobRepository.save(job);
    }
}