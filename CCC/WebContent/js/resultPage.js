var number = 0;
function getNext() {
	if (number === 5) {
	} else {
		var compartments = new Array("coupling", "size", "variable", "method",
				"inheritance", "controlStructure");
		var status = 'false';
		for ( var compartment in compartments) {

			var element = document.getElementById(compartments[compartment]), style = window
					.getComputedStyle(element), display = style
					.getPropertyValue('display');

			if (compartment < compartments.length) {
				if (status === 'true') {
					document.getElementById(compartments[compartment]).style.display = "block";
					break;
				}

				if (display === 'block') {
					status = 'true';
					document.getElementById(compartments[compartment]).style.display = "none";
				}
			}

		}
		number += 1;
	}
}

function getPrevious() {
	
	if (number > 0 && number < 6) {
	
		var compartments = new Array("controlStructure", "inheritance",
				"method", "variable", "size", "coupling");
		var status = 'false';
		for ( var compartment in compartments) {

			var element = document.getElementById(compartments[compartment]), style = window
					.getComputedStyle(element), display = style
					.getPropertyValue('display');

			if (compartment < compartments.length) {
				if (status === 'true') {
					document.getElementById(compartments[compartment]).style.display = "block";
					break;
				}

				if (display === 'block') {
					status = 'true';
					document.getElementById(compartments[compartment]).style.display = "none";
				}
			}

		}
		number -= 1;
	}
}