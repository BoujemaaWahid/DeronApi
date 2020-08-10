package com.deron.demo.services;

import com.deron.demo.daos.PersonDAO;
import com.deron.demo.daos.TraceDAO;
import com.deron.demo.dtos.LoginDto;
import com.deron.demo.dtos.PersonDto;
import com.deron.demo.dtos.TraceDto;
import com.deron.demo.entitys.Person;
import com.deron.demo.entitys.Trace;
import com.deron.demo.security.jwt.JwtGenerator;
import com.deron.demo.security.jwt.JwtUser;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    public Long savePerson(PersonDto personDto){
        Person person = new Person();
        personDto.setPassword( bCryptPasswordEncoder.encode( personDto.getPassword() ));
        modelMapper.map(personDto, person);
        return personDAO.saveAndFlush(person).getId();
    }
    public Long saveTrace(TraceDto traceDto){
        Trace trace = new Trace();
        modelMapper.map(traceDto, trace);
        return traceDAO.saveAndFlush(trace).getId();
    }
    public LoginDto canLogin(String email, String password){
        LoginDto loginDto = new LoginDto();
        Person p = personDAO.findPersonByEmail(email);
        if ( p == null )return loginDto;
        boolean match = bCryptPasswordEncoder.matches( password, p.getPassword() );
        if( !match )return loginDto;
        RandomString rs = new RandomString(15);
        JwtUser jwtUser = new JwtUser();
        jwtUser.setId( p.getId() );
        jwtUser.setRole("ROLE_USER");
        jwtUser.setUsername( p.getUsername() );
        jwtUser.setPassword(rs.nextString());
        String token = jwtGenerator.generate(jwtUser);
        loginDto.setId( p.getId() );
        loginDto.setToken( token );
        loginDto.setError(false);
        return loginDto;
    }
}
