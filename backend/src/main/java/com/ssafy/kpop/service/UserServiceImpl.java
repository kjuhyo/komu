package com.ssafy.kpop.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.kpop.dao.UserDao;
import com.ssafy.kpop.dto.LoginCallBackDto;
import com.ssafy.kpop.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userdao;

	//provider 정보로 UserDto 값 읽어오기
	@Override
	public UserDto findByProvider(HashMap<String, Object> user) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("provider", (String) user.get("provider"));
		map.put("provider_id", (String) user.get("provider_id"));
		return userdao.findByProvider(map);
	}

	//uid로 UserDto 값 읽어오기
	@Override
	public UserDto findById(String uid) {
		return userdao.findById(uid);
	}

	//최초 로그인에 필요한 save
	@Override
	public LoginCallBackDto save(HashMap<String, Object> user) {
		String uid=(String) user.get("uid");
		String nickname=(String) user.get("nickname");
		String profile=(String) user.get("profile");
		String provider=(String) user.get("provider");
		String provider_id=(String) user.get("provider_id");
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
		UserDto u=new UserDto(uid, nickname, profile, provider, provider_id);
		LoginCallBackDto loginCallBackDto=new LoginCallBackDto();
		
		loginCallBackDto.setUid(uid);
		loginCallBackDto.setNickname(nickname);
		loginCallBackDto.setProfile(profile);
		loginCallBackDto.setProvider(provider_id);
		
		return loginCallBackDto;
	}

	//User 닉네임 업데이트
	@Override
	public void updateUser(UserDto user) {
		userdao.updateUser(user);
		return;
	}

	
	
	
}
