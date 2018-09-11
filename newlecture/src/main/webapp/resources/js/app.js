window.addEventListener("load", function(event){
	var editorTarget = document.querySelector("textarea");
	var parent = editorTarget.parentElement;
	
	var webEditor = document.createElement("div") ; // 로드해야하고 
	webEditor.style.background = yellow;
	
	var request = new XMLHttpRequest();
	request.onload = function(e){
		webEditor.innerHTML = request.responseText;
		editorTarget.after(webEditor);
		//webEditor를 editorTarget 동생으로 추가해야 한다.
		
		
	};
	request.open("GET","template.html",true);
	request.send
	
});