package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.Team;
import com.springboot.MyTodoList.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping(value = "/teams")
    public List<Team> getAllTeams() {
        return teamService.findAll();
    }

    @GetMapping(value = "/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(teamService.getTeamById(id).getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/teams")
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        Team saved = teamService.addTeam(team);
        HttpHeaders headers = new HttpHeaders();
        headers.set("location", String.valueOf(saved.getTeamId()));
        headers.set("Access-Control-Expose-Headers", "location");
        return ResponseEntity.ok().headers(headers).build();
    }

    @PutMapping(value = "/teams/{id}")
    public ResponseEntity<Team> updateTeam(@RequestBody Team team, @PathVariable int id) {
        try {
            Team updated = teamService.updateTeam(id, team);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/teams/{id}")
    public ResponseEntity<Boolean> deleteTeam(@PathVariable int id) {
        Boolean flag = false;
        try {
            flag = teamService.deleteTeam(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
