var number = 0;

window.addEventListener('load', (event) => {
	var url_string = window.location.href;
	var url = new URL(url_string);
	var tab = url.searchParams.get("edited");
	
	if(tab != null){
		changeClass(document.getElementById("sizeBtn"), "activated", " fBtn");
		var compartments = new Array("size", "variable", "method", "coupling",
			"controlStructure", "inheritance", "finalResult");
	
		for ( var compartment in compartments) {
			if(tab === compartments[compartment]){
				document.getElementById(compartments[compartment]).style.display = "block";
				/*document.getElementById(compartments[compartment]+"Btn").style.backgroundColor = "lightGrey";
				document.getElementById(compartments[compartment]+"Btn").style.color = "black";*/
				changeClass(document.getElementById(compartments[compartment]+"Btn"), "fBtn", " fBtn_clicked");
			}else{
				document.getElementById(compartments[compartment]).style.display = "none";
				/*document.getElementById(compartments[compartment]+"Btn").style.backgroundColor = "Black";
				document.getElementById(compartments[compartment]+"Btn").style.color = "white";*/
				changeClass(document.getElementById(compartments[compartment]+"Btn"), "fBtn_clicked", " fBtn");
			}
		}
		
		for(var compartment in compartments){
			if(tab === compartments[compartment]){
				break;
			}
			number += 1;
		}
	}
});

function instantGo(type){
	number = -1;
	if (type === undefined) {
		type = "";
	}  

	if(type === ""){
		
	}else{
		changeClass(document.getElementById("sizeBtn"), "activated", " fBtn");
		var compartments = new Array("size", "variable", "method", "coupling",
				"controlStructure", "inheritance", "finalResult");
		var status = 'false';
		for ( var compartment in compartments) {
			
			if(status === 'false'){
				number += 1;
			}
			
			if(type === compartments[compartment]){
				status = 'true';
				document.getElementById(compartments[compartment]).style.display = "block";
				changeClass(document.getElementById(compartments[compartment]+"Btn"), "fBtn", " fBtn_clicked");
			}else{
				document.getElementById(compartments[compartment]).style.display = "none";
				changeClass(document.getElementById(compartments[compartment]+"Btn"), "fBtn_clicked", " fBtn");
			}
		}
		
	}
	
}

function getNext() {
	changeClass(document.getElementById("sizeBtn"), "activated", " fBtn");
	var url_string = window.location.href;
	var url = new URL(url_string);
	var tab = url.searchParams.get("tab");
	
	if(number < 0 && tab != null){
		if (number === 6) {
		} else {
			var compartments = new Array("size", "variable", "method", "coupling",
					"controlStructure", "inheritance", "finalResult");
			var status = 'false';
			var next = 'false';
			for ( var compartment in compartments) {
	
				if(next === 'false'){
					if(tab === compartments[compartment]){
						next = 'true';
					}
				}
				
				if(next === 'true'){
					var element = document.getElementById(compartments[compartment]), style = window
							.getComputedStyle(element), display = style
							.getPropertyValue('display');
		
					if (compartment < compartments.length) {
						if (status === 'true') {
							document.getElementById(compartments[compartment]).style.display = "block";
							changeClass(document.getElementById(compartments[compartment]+"Btn"), "fBtn", " fBtn_clicked");
							break;
						}
		
						if (display === 'block') {
							status = 'true';
							document.getElementById(compartments[compartment]).style.display = "none";
							changeClass(document.getElementById(compartments[compartment]+"Btn"), "fBtn_clicked", " fBtn");
						}
					}
		
				}
			}
			number += 1;
		}
	}else{
		if (number === 6) {
		} else {
			var compartments = new Array("size", "variable", "method", "coupling",
					"controlStructure", "inheritance", "finalResult");
			var status = 'false';
			for ( var compartment in compartments) {
	
				var element = document.getElementById(compartments[compartment]), style = window
						.getComputedStyle(element), display = style
						.getPropertyValue('display');
	
				if (compartment < compartments.length) {
					if (status === 'true') {
						document.getElementById(compartments[compartment]).style.display = "block";
						changeClass(document.getElementById(compartments[compartment]+"Btn"), "fBtn", " fBtn_clicked");
						break;
					}
	
					if (display === 'block') {
						status = 'true';
						document.getElementById(compartments[compartment]).style.display = "none";
						changeClass(document.getElementById(compartments[compartment]+"Btn"), "fBtn_clicked", " fBtn");
					}
				}
	
			}
			number += 1;
		}
	}
}

function getPrevious() {
	changeClass(document.getElementById("sizeBtn"), "activated", " fBtn");
	var url_string = window.location.href;
	var url = new URL(url_string);
	var tab = url.searchParams.get("tab");
	
	if(tab != null){
		if (number > 0 && number < 7) {
			var compartments = new Array("finalResult", "inheritance", "controlStructure", "coupling",
					"method", "variable", "size");
			var status = 'false';
			for ( var compartment in compartments) {

					var element = document.getElementById(compartments[compartment]), style = window
							.getComputedStyle(element), display = style
							.getPropertyValue('display');
		
					if (compartment < compartments.length) {
						if (status === 'true') {
							document.getElementById(compartments[compartment]).style.display = "block";
							changeClass(document.getElementById(compartments[compartment]+"Btn"), "fBtn", " fBtn_clicked");
							break;
						}
		
						if (display === 'block') {
							status = 'true';
							document.getElementById(compartments[compartment]).style.display = "none";
							changeClass(document.getElementById(compartments[compartment]+"Btn"), "fBtn_clicked", " fBtn");
						}
					}
				}
			number -= 1;
		}
	}else{
		if (number > 0 && number < 7) {
			
			var compartments = new Array("finalResult", "inheritance", "controlStructure", "coupling",
					"method", "variable", "size");
			var status = 'false';
			for ( var compartment in compartments) {
	
				var element = document.getElementById(compartments[compartment]), style = window
						.getComputedStyle(element), display = style
						.getPropertyValue('display');
	
				if (compartment < compartments.length) {
					if (status === 'true') {
						document.getElementById(compartments[compartment]).style.display = "block";
						changeClass(document.getElementById(compartments[compartment]+"Btn"), "fBtn", " fBtn_clicked");
						break;
					}
	
					if (display === 'block') {
						status = 'true';
						document.getElementById(compartments[compartment]).style.display = "none";
						changeClass(document.getElementById(compartments[compartment]+"Btn"), "fBtn_clicked", " fBtn");
					}
				}
	
			}
			number -= 1;
		}
	}
}

function changeClass(object,oldClass,newClass)
{
    var regExp = new RegExp('(?:^|\\s)' + oldClass + '(?!\\S)', 'g');
    object.className = object.className.replace( regExp , newClass );
}