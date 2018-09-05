<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<main>
	<section id="category">
		<h1>분류선택</h1>
		<div>
			<div><input type="checkbox" id="last" /><label for="last">최근 설정</label></div>
			<div>객관식문제(<a href="">변경</a>)</div>
			<div><a href="">교과목 관리</a></div>
		</div>
	</section>
	<section>
		<h1>문제 카테고리</h1>
		
		<ul>
			
			<li>
				<label>교과목</label>
				<select>
					<option>과목선택</option>
					<option>자바기초 </option>
				</select>
			</li>
			
			<li>
				<label>교과단원</label>
				<select>
					<option>단원선택</option>
					<option>제어구조</option>
				</select>					
			</li>
			
			<li>
				<label>난이도</label>
				<select>
					<option>난이도선택</option>
					<option>상</option>
				</select>
			</li>
		
		</ul>
	</section>
</main>