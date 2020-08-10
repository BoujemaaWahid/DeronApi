package com.deron.demo.services;

import com.deron.demo.daos.TraceDAO;
import com.deron.demo.dtos.LoginBodyDto;
import com.deron.demo.dtos.TraceDto;
import com.deron.demo.entitys.Person;
import com.deron.demo.entitys.Trace;
import org.modelmapper.ModelMapper;

public class TraceSaver extends Thread {
    private LoginBodyDto loginBodyDto;
    private TraceDAO traceDAO;
    private Long id;
    private ModelMapper modelMapper;
    public TraceSaver(ModelMapper modelMapper, Long id, LoginBodyDto loginBodyDto, TraceDAO traceDAO){
        this.loginBodyDto = loginBodyDto;
        this.traceDAO = traceDAO;
        this.id = id;
        this.modelMapper = modelMapper;
        this.run();
    }
    @Override
    public void run() {
        super.run();
        Person person = new Person();
        Trace trace = new Trace();
        TraceDto dto = new TraceDto();
        person.setId( this.id );
        dto.setConnection_date( this.loginBodyDto.getConnection_date() );
        dto.setConnection_device( this.loginBodyDto.getConnection_device() );
        dto.setConnection_place( this.loginBodyDto.getConnection_place() );
        dto.setSystem_device( this.loginBodyDto.getSystem_device() );
        dto.setPerson( person );
        dto.setTrust_connection(true);
        modelMapper.map(dto, trace);
        this.traceDAO.save( trace );
    }
}
