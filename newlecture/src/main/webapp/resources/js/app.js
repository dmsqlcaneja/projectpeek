//
window.addEventListener("load", function(event){
	var editorTarget = document.querySelector("textarea");
	var parent = editorTarget.parentElement;

	var width = window
					.getComputedStyle(editorTarget,null)
					.getPropertyValue("width");
	
	var height = window 
					.getComputedStyle(editorTarget,null)
					.getPropertyValue("height");

	
	var webEditor = document.createElement("div") ; // 로드해야하고 
	webEditor.contentEditable = true;
	webEditor.style.background = "yellow";
	webEditor.style.width = width;
	webEditor.style.height = height;
	
	

	var request = new XMLHttpRequest();
	//template 로드됨. 
	request.onload = function(e){
		webEditor.innerHTML = request.responseText;
		editorTarget.after(webEditor);

		var iframe = webEditor.querySelector("iframe");
		var win = iframe.contentWindow;
		

		var boldButton = webEditor.querySelector(".bold-button");

		boldButton.onclick = function(e){
			var doc = win.document;
			
			boldButton.onclick = function(e){
				doc.execCommand("bold");
			}

		};
		
		
	};
	request.open("GET","toolbar.html",true);
	request.send();
	
});