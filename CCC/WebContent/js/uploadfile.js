window.addEventListener('load', (event) => {
	document.getElementById("alertError").style.display = "none";
});

function uploadOnChange(e) {
    var filename = e.value;
    var lastIndex = filename.lastIndexOf("\\");
    if (lastIndex >= 0) {
        filename = filename.substring(lastIndex + 1);
    }
    document.getElementById('filename').value = filename;
}

function fileCheck(){
	
	var filename = document.getElementById("filename").value;
	var res = filename.split(".");
	if(filename == ""){
		document.getElementById("alertError").innerHTML = "Select a file first.";
		document.getElementById("alertError").style.display = "block";
		return false;
	}else if(res[1] != "zip" && res[1] != "java" && res[1] != "cpp"){
		document.getElementById("alertError").innerHTML = "Wrong file format. only zip, java or cpp files support.";
		document.getElementById("alertError").style.display = "block";
		return false;
	}
	
}