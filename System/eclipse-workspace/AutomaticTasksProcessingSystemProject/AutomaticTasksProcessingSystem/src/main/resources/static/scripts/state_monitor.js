/**
 * 
 */
let currentState = "";

function state_refresh() {
	get_state();
	setInterval("get_state();",5000); 
}

function get_state() {
	$.ajax({
		type: "GET",
        url: "/state-change",
        success: function (data) {
			if(currentState != data["display"]){
				currentState = data["display"];
				$("#engineState").removeAttr("class");
				$("#engineState").addClass(data["displayClass"]);
				$("#engineState").val(data["display"]);
				$("#admin-submit").val(data["buttonLabel"]);
				$("#action").val(data["buttonAction"]);
			}
			if(data["onTransition"]) {
				$("#admin-submit").prop("disabled", true);
			} else {
				$("#admin-submit").prop("disabled", false);
			}

        },
        error: function (e) {
        }
    });
}
