<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- /WEB-INF/views/user/join.jsp -->
<%@ include file="../common/header.jsp" %>
<style>
	table{
		max-width:500px;
		margin : 0 auto;
	}
	
	#uploadImage{
		width:100px;
		height:100px;
		border-radius:50px;
		border:1px solid #ccc;	
	}
</style>
<form id="joinForm" action="${path}/user/joinPost" method="POST" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<th colspan="2">
				<h1>JOIN PAGE</h1>
			</th>
		</tr>
		<tr>
			<td>프로필 이미지</td>
			<td style="text-align:center;">
				<img src="${path}/resources/img/profile.jpg" id="uploadImage"/>
				<br/>
				<input type="file" id="profileImage" name="profileImage" accept="image/*"/>
			</td>
		</tr>
		<tr>
			<td>아이디(e-mail)</td>
			<td>
				<input type="text" name="u_id" id="u_id" autocomplete="off" />
				<button type="button" id="acceptEmail">이메일 인증</button>
				<!-- 검증 결과를 출력할 박스모델 -->
				<div class="result"></div>
				<div id="emailCodeWrap">
					<input type="text" id="emailCode" />
					<button type="button" id="emailAcceptBtn">인증완료</button>
				</div>
			</td>
		</tr>
		<!-- 비밀번호 -->
		<tr>
			<td>비밀번호</td>
			<td>
				<input type="password" name="u_pw" id="u_pw" />
				<div class="result"></div>
			</td>
		</tr>
		<!-- 비밀번호 확인-->
		<tr>
			<td>비밀번호 확인</td>
			<td>
				<input type="password" name="re_pw" id="re_pw" />
				<div class="result"></div>
			</td>
		</tr>
		<!-- 이름 -->
		<tr>
			<td>이름(한글2~6자이내)</td>
			<td>
				<input type="text" name="u_name" id="u_name" />
				<div class="result"></div>
			</td>
		</tr>
		<!-- 생년월일 -->
		<tr>
			<td>생년월일(ex-19950505)</td>
			<td>
				<input type="text" name="u_birth" id="u_birth" />
				<div class="result"></div>
			</td>
		</tr>
		<!-- 주소 -->
		<tr>
			<td>주소</td>
			<td>
				<div>
					<input type="text" readonly name="u_addr_post" id="u_post" class="addr" placeholder="우편번호"/>
					<input type="button" id="findAddr" value="주소찾기" />
				</div>
				<input type="text" readonly name="u_addr" id="u_addr" class="addr" placeholder="주소"/>
				<input type="text" name="u_addr_detail" id="u_addr_detail" class="addr" placeholder="상세주소"/>
			</td>
		</tr>
		<!-- 전화번호 -->
		<tr>
			<td>전화번호(-제외 숫자만입력)</td>
			<td>
				<div id="phoneWrap">
					<input type="text" name="u_phone" id="u_phone" />
					<div class="result"></div>
				</div>
			</td>
		</tr>
		<!-- 개인정보처리방침 -->
		<!-- https://www.privacy.go.kr -->
		<tr>
			<td colspan="2">
				<h4>개인정보처리방침</h4>
				<textarea readonly cols=50 rows=5>당신의 개인정보는 언제든지 회사에서 팔아먹을 수 있으며 3자에게 항상 양도 됩니다. 그래도 이용하시겠습니까?</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label>
					<input type="checkbox" name="u_info" id="u_info" value="y"/>
					개인정보 이용 동의
				</label>
				<div class="result"></div>
			</td>
		</tr>
		<tr>
			<th colspan="2">
				<button>Submit</button>
			</th>
		</tr>
	</table>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		$("#findAddr").click(function(){
		    new daum.Postcode({
		        oncomplete: function(data) {
		            console.log(data);
		            
		            var fullAddr = "";			// 최종 주소
		            var extraAddr = "";			// 조합형 주소
		            var postCode = "";			// 우편번호
					
		            if(data.userSelectedType == 'R'){
		            	// 도로명 주소
		            	fullAddr = data.roadAddress;
		            	
		            	// 법정동명
		            	if(data.bname !== ''){
		            		extraAddr += data.bname;
		            	}
		            	
		            	// 건물명
		            	if(data.buildingName !== ''){
		            		extraAddr += (extraAddr !== '' ? ', '+data.buildingName : data.buildingName);
		            	}
		            	
		            	fullAddr += extraAddr !== '' ? '('+extraAddr+')' : '';
		            	
		            }else{
		            	// 지번 주소
		            	fullAddr = data.jibunAddress;
		            }
		            // 우편번호
		            postCode = data.zonecode;
		            // 입력필드에 값 넣기
		            $("#u_post").val(postCode);
		            $("#u_addr").val(fullAddr);
		            $("#u_addr_detail").focus();
		        }
		    }).open();
		});
	</script>

	
	<script>
		// 기본 프로필 이미지 경로
		let imagePath = $("#uploadImage").attr("src"); 
		// 사용자가 선택 또는 입력한 값이 변경 되었을 때 발생되는 이벤트
		$("#profileImage").on("change", function(){
			// this == change event가 발생된 입력 양식 태그 == #profileImage
			let files = $(this)[0].files;
			console.log(files);
			let file = files[0];
			if(file != null && file.type.startsWith("image/")){
				// image 형식의 파일
				console.log(file.type);
				// 사용자에 PC에 저장된 file 경로 추출
				let path = window.URL.createObjectURL(file);
				$("#uploadImage").attr("src",path);
			}else{
				alert("이미지를 선택해주세요.");
				$(this)[0].files = null;
				$(this).val(null);
				$("#uploadImage").attr("src",imagePath);
			}
		});
	</script>
</form>
</body>
</html>









