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
	public LoginCallBackDto save(HashMap<String, Object> user) throws Exception {
		String uid=makeUid();
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
		
		userdao.save(u);
		
		//System.out.println("서비스콜백"+loginCallBackDto);
		return loginCallBackDto;
	}

	/**
	 * 13자리 난수값 생성 Uid 중복체크
	 * */
	public String makeUid() throws Exception {
		UserDto user = null;
		StringBuilder sb = new StringBuilder();
		while (true) {
			String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

			for (int i = 0; i < 12; i++) {
				sb.append(str.charAt((int) (Math.random() * str.length())));
			}
			//System.out.println("투스트링"+sb.toString());
			user = findById(sb.toString());
			if (user == null)
				break;
		}
		return sb.toString();
	}
	
	//User 닉네임 업데이트
	@Override
	public void updateUser(UserDto user) {
		userdao.updateUser(user);
		return;
	}

	@Override
	public String findNickName(String uid) {
		return userdao.findNickName(uid);
	}

	
	
	
}
