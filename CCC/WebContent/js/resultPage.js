var number = 0;
function getNext() {
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
					document.getElementById(compartments[compartment]+"Btn").style.backgroundColor = "lightGrey";
					document.getElementById(compartments[compartment]+"Btn").style.color = "black";
					break;
				}

				if (display === 'block') {
					status = 'true';
					document.getElementById(compartments[compartment]).style.display = "none";
					document.getElementById(compartments[compartment]+"Btn").style.backgroundColor = "Black";
					document.getElementById(compartments[compartment]+"Btn").style.color = "white";
				}
			}

		}
		number += 1;
	}
}

function getPrevious() {

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
					document.getElementById(compartments[compartment]+"Btn").style.backgroundColor = "lightGrey";
					document.getElementById(compartments[compartment]+"Btn").style.color = "black";
					break;
				}

				if (display === 'block') {
					status = 'true';
					document.getElementById(compartments[compartment]).style.display = "none";
					document.getElementById(compartments[compartment]+"Btn").style.backgroundColor = "Black";
					document.getElementById(compartments[compartment]+"Btn").style.color = "white";
				}
			}

		}
		number -= 1;
	}
}