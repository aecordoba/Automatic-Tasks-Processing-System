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
				case "STARTING":
					$("#engineState").addClass("starting");
					break;
				case "RUNNING":
					$("#engineState").addClass("running");
					break;
				case "STOPPING":
					$("#engineState").addClass("stopping");
					break;
			};
			$("#engineState").val(data["state"]);
        },
        error: function (e) {
        }
    });
}
