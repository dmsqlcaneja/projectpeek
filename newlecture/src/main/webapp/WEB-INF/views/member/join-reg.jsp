<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<main>
	<section id="form-section">
	<!-- 단위 프로그래밍이 유행중  -->
		<h1>회원가입 페이지</h1>
		<form method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>
						<label>사진 : </label>
						<img class="photo" alt="" src="">
						<input type="file" value="사진선택" hidden="true" name="photo-file" />
						<span class="foto-button">사진선택</span>
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
				<tr>
					<td>
						<input type="submit" value="회원가입" />
					</td>
				</tr>
			</table>
		</form>
		 
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
		var submitButton = formSection.querySelector("input[type=submit]")
		var idOk = false;
		
		var fotoButton = formSection.querySelector(".foto-button")
		var fileButton = formSection.querySelector("input[type=file]");
		var photo = formSection.querySelector(".photo");



		fileButton.onchange = function(e){

			var file = fileButton.files[0];

			//파일 타입 확인 
			if(file.type.indexOf('image/') < 0 ){
				alert("이미지형식이 아닙니다.");
				return;
			};

			//파일 사이즈 확인 
			if(file.size > 1024*1024*10){
				alert("이미지는 10MB를 초과할 수 없습니다.");
				return;
			}
			

			var reader = new FileReader();
			//다운로드가 완료되면 진행하라. 
			reader.onload = function(evt){
				photo.src = evt.target.result;
			};
			reader.readAsDataURL(file);

			
		};

		//트리거 활용 사진 업로드 
		fotoButton.onclick = function(e){
			
			var event = new MouseEvent("click", {
	            'view': window,
	            'bubbles':true,
	            'cancelable':true
	        });
			fileButton.dispatchEvent(event);
	        
		};

		submitButton.onclick = function(e){
			
			

			if(!idOk){
				alert("아이디 중복확인을 해주세요.");
				e.preventDefault();
			}


		};
		
		idCheckButton.onclick = function(e){
			
			alert("test");
			//ajax -> 협력자 백엔드에게 연락해서 알아봐야함. 
			// member/is-id-duplicated 
			
			var id = idInput.value;

			
			var request = new XMLHttpRequest();
			
			request.onload = function(e){
				
				var duplicated = JSON.parse(request.responseText);

				if(duplicated){
					alert("이미 사용중인 아이디 입니다.");
					return;
				}

				//idOk = ;
				
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