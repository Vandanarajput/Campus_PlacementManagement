package com.PlacementManagementSystem.Placement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.PlacementManagementSystem.Placement.model.StudentSkills;
import com.PlacementManagementSystem.Placement.repository.StudentSkillsRepository;

@Service
public class StudentSkillsService {

    @Autowired
    private StudentSkillsRepository skillRepo;

    public List<StudentSkills> getAllSkills() {
        return skillRepo.findAll();
    }

    public StudentSkills addSkill(StudentSkills skill) {
        return skillRepo.save(skill);
    }

    public void deleteSkill(Long id) {
        skillRepo.deleteById(id);  // Deletes the skill by its ID
    }

}
