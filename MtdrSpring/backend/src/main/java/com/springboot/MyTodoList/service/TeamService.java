package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.Team;
import com.springboot.MyTodoList.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public ResponseEntity<Team> getTeamById(int id) {
        Optional<Team> data = teamRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Team getTeamByIdSimple(int id) {
        Optional<Team> data = teamRepository.findById(id);
        return data.orElse(null);
    }

    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }

    public boolean deleteTeam(int id) {
        try {
            teamRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Team updateTeam(int id, Team updated) {
        Optional<Team> data = teamRepository.findById(id);
        if (data.isPresent()) {
            Team team = data.get();
            team.setName(updated.getName());
            team.setDescription(updated.getDescription());
            team.setIsDeleted(updated.getIsDeleted());
            team.setDeletedAt(updated.getDeletedAt());
            return teamRepository.save(team);
        } else {
            return null;
        }
    }
}
