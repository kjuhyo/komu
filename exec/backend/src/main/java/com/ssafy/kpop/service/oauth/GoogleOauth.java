package com.ssafy.kpop.service.oauth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Component
public class GoogleOauth implements SocialOauth{
	
	public static final Logger logger = LoggerFactory.getLogger(GoogleOauth.class);
	
	// user정보가져오기
		public HashMap<String, Object> getUserInfoFromOauth(String accessToken) {

			// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
			HashMap<String, Object> userInfo = new HashMap<>();
			String reqURL = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token="+accessToken;
			
			try {
				URL url = new URL(reqURL);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();

				int responseCode = conn.getResponseCode();
				logger.info("#responseCode : " + responseCode);

				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String line = "";
				String result = "";

				while ((line = br.readLine()) != null) {
					result += line;
				}
				logger.info("#response body : " + result);
				
				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(result);

				// 인증수단, 인증 서버내 pk값, 닉네임, 프로필 사진
				String provider = "google";
				String provider_id = element.getAsJsonObject().get("sub").getAsString();
				String nickname = element.getAsJsonObject().get("name").getAsString();
				String profileImg = element.getAsJsonObject().get("picture").getAsString();
				String uid="";
				
				userInfo.put("uid", uid);
				userInfo.put("nickname", nickname);
				userInfo.put("profile", profileImg);
				userInfo.put("provider", provider);
				userInfo.put("provider_id", provider_id);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			return userInfo;
		}

}
