package com.ssafy.kpop.error;

public class UnauthorizedException extends RuntimeException{
	
	//serialVersionUID 는 직렬화에 사용되는 고유 아이디인데, 선언하지 않으면 JVM에서 디폴트로 자동 생성된다.
	//따라서 선언하지 않아도 동작하는데 문제는 없지만, 불안하기 때문에 JAVA에서는 명시적으로 serialVersionUID를 선언할 것을 적극 권장하고 있다.

	private static final long serialVersionUID = -2238030302650813813L;
	
	public UnauthorizedException() {
		super("계정권한이 유효하지 않으므로 다시 로그인 해주세요.");
	}
}
