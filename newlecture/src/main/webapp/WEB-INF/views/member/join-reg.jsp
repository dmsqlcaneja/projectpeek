<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<main>
	<section id="form-section">
	<!-- 단위 프로그래밍이 유행중  -->
		<h1>회원가입 페이지</h1>
		<form method="post">
			<table>
				<tr>
					<td>
						<label>사진 : </label>
						<img alt="" src="">
						<input type="file" value="사진선택" hidden="true" />
						<span>사진선택</span>
					</td>
					<td>
						<label>아이디 : </label>
						<input name="id" value="${uid}"/> <!-- 패턴사용으로 아이디 제약조건 -->
						<input type="button" class="id-check-button" value="중복조회" required="required" />
					</td>
					<td>
						<label>비밀번호 : </label>
						<input name="pwd" required="required" />
					</td>
					<td>
						<label>이름 : </label>
						<input name="name" required="required"/>
					</td>
					<td>
						<label>이메일 : </label>
						<input name="email" readonly="readonly" value="${email}"/>
					</td>
				</tr>
			</table>
		</form>
		아이디,이름,이메일,생년월일,주소,전화번호,닉네임,성별,비밀번호, 
	</section>
</main>

<script type="text/javascript">
	/*
	파일 업로드를 위한 방법 
	아이디 중복검사를 위한 방법 
	
	*/
	
	window.addEventListener("load", function(event){
		var formSection = document.querySelector("#form-section");
		var idCheckButton = formSection.querySelector(".id-check-button");
		var idInput = formSection.querySelector("input[name=id]");
		
		idCheckButton.onclick = function(e){
			
			alert("test");
			//ajax -> 협력자 백엔드에게 연락해서 알아봐야함. 
			// member/is-id-duplicated 
			
			var id = idInput.value;

			
			var request = new XmlhttpRequest();
			
			request.onload = function(e){
				
				if(request.status != 200) {
					alert("서버상의 오류가 발생하였습니다.");
				    return;
				}
		
				if(request.responseText == "true") {
					alert("이미 아이디 있음 ㅎㅎ");
					return;
				}
				
			}
			
			request.open("GET", "is-id-duplicated?+id="+id, true);
			request.send();
			
		};
		
	});

</script>