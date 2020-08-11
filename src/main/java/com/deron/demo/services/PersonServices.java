package com.deron.demo.services;

import com.deron.demo.daos.PersonDAO;
import com.deron.demo.daos.TraceDAO;
import com.deron.demo.dtos.*;
import com.deron.demo.entitys.Person;
import com.deron.demo.entitys.Trace;
import com.deron.demo.security.jwt.JwtGenerator;
import com.deron.demo.security.jwt.JwtUser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class PersonServices {
    @Autowired
    private JwtGenerator jwtGenerator;
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private TraceDAO traceDAO;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ObjectMapper objectMapper;

    private void updateSignature(Long person_id){
        Person person = personDAO.findById(person_id).get();
        ObjectNode signature_v1 = objectMapper.createObjectNode();
        signature_v1.put("id", person.getId());
        signature_v1.put("username", person.getUsername());
        signature_v1.put("email", person.getEmail());
        signature_v1.put("created_date", person.getCreated_date().toString());
        String signature = new String(Base64.getEncoder().encode(signature_v1.toString().getBytes()));
        person.setSignature( signature );
        personDAO.save( person );
    }

    public Long savePerson(PersonDto personDto){
        Person person = new Person();
        personDto.setPassword( bCryptPasswordEncoder.encode( personDto.getPassword() ));
        modelMapper.map(personDto, person);
        Long id = personDAO.saveAndFlush(person).getId();
        this.updateSignature( id );
        return id;
    }

    public void deconnect(DeconnectBodyDto disconnect){
        Trace trace = traceDAO.findById( disconnect.getId() ).get();
        TraceDto dto = new TraceDto();
        modelMapper.map(trace, dto);
        Person person = personDAO.findById(disconnect.getPerson()).get();
        person.setOffline(true);
        personDAO.save( person );
        dto.setDeconnection_date( disconnect.getDate() );
        modelMapper.map(dto, trace);
        traceDAO.save( trace );
    }

    public LoginResponseDto canLogin(LoginBodyDto loginBodyDto){
            LoginResponseDto loginResponseDto = new LoginResponseDto();
            Trace trace = new Trace();
            Person p = personDAO.findPersonByEmail(loginBodyDto.getEmail());
            if (p == null) return loginResponseDto;
            boolean match = bCryptPasswordEncoder.matches(loginBodyDto.getPassword(), p.getPassword());
            if (!match) return loginResponseDto;
            loginBodyDto.getTrace().setPerson(p);
            RandomString rs = new RandomString(15);
            JwtUser jwtUser = new JwtUser();
            jwtUser.setId(p.getId());
            jwtUser.setRole("ROLE_USER");
            jwtUser.setUsername(p.getUsername());
            jwtUser.setPassword(rs.nextString());
            String token = jwtGenerator.generate(jwtUser);
            loginResponseDto.setUser_id(p.getId());
            loginResponseDto.setToken(token);
            loginResponseDto.setError(false);
            loginResponseDto.getCon_id();
            modelMapper.map(loginBodyDto.getTrace(), trace);
            loginResponseDto.setCon_id(traceDAO.saveAndFlush(trace).getId());
            p.setOffline(false);
            personDAO.save(p);
            return loginResponseDto;
    }
}
