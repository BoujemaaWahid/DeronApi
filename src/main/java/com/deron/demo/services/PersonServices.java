package com.deron.demo.services;

import com.deron.demo.daos.PersonDAO;
import com.deron.demo.daos.TraceDAO;
import com.deron.demo.dtos.LoginBodyDto;
import com.deron.demo.dtos.LoginResponseDto;
import com.deron.demo.dtos.PersonDto;
import com.deron.demo.entitys.Person;
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

    public LoginResponseDto canLogin(LoginBodyDto loginBodyDto){
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        Person p = personDAO.findPersonByEmail(loginBodyDto.getEmail());
        if ( p == null )return loginResponseDto;
        boolean match = bCryptPasswordEncoder.matches( loginBodyDto.getPassword(), p.getPassword() );
        if( !match )return loginResponseDto;
        RandomString rs = new RandomString(15);
        JwtUser jwtUser = new JwtUser();
        jwtUser.setId( p.getId() );
        jwtUser.setRole("ROLE_USER");
        jwtUser.setUsername( p.getUsername() );
        jwtUser.setPassword(rs.nextString());
        String token = jwtGenerator.generate(jwtUser);
        loginResponseDto.setId( p.getId() );
        loginResponseDto.setToken( token );
        loginResponseDto.setError(false);
        new TraceSaver(this.modelMapper, p.getId(), loginBodyDto, traceDAO);
        return loginResponseDto;
    }
}
