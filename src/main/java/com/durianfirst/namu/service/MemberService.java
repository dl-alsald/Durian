package com.durianfirst.namu.service;

import com.durianfirst.namu.dto.MemberDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    static class MidExistException extends Exception{

    }
    void join(MemberDto memberDto) throws MidExistException;
}
