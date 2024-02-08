/**
 * 
 */

function state_refresh() {
	get_state();
	setInterval("get_state();",5000); 
}

function get_state() {
	$.ajax({
		type: "GET",
        url: "/state-change",
        success: function (data) {
			$("#engineState").removeAttr("class");
			switch(data["state"]){
				case "STOPPED":
					$("#engineState").addClass("stopped");
					break;
				case "RUNNING":
					$("#engineState").addClass("running");
			};
			$("#engineState").val(data["state"]);
			if(data["stateTransition"]) {
				$("#admin-submit").prop("disabled", true);
			} else {
				$("#admin-submit").prop("disabled", false);
			}
        },
        error: function (e) {
        }
    });
}
