package com.durianfirst.durian.service;

import com.durianfirst.durian.dto.MemberJoinDTO;

public interface MemberService {

    static class MidExistException extends Exception{} //static으로 선언해 MidExistException가 필요한 곳에 쓰이도록 함

    void register(MemberJoinDTO memberJoinDTO) throws MidExistException; //만일 같은 아이디가 존재하면 예외 발생
}
