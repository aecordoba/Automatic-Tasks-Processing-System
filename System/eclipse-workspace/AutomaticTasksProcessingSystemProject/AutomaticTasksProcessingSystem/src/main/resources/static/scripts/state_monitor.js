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
			if(currentState != data["state"]){
				currentState = data["state"];
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
				};
				$("#engineState").val(data["state"]);
				if(data["state"] == "STARTING" || data["state"] == "STOPPING") {
					$("#admin-submit").prop("disabled", true);
				} else {
					$("#admin-submit").prop("disabled", false);
				}
				
				if(data["state"] == "STOPPED") {
					$("#admin-submit").val("[[#{systemcontrol.button.start}]]");
				}

				if(data["state"] == "RUNNING") {
					$("#admin-submit").val(/*[[#{systemcontrol.button.stop}]]*/);
				}
			}
        },
        error: function (e) {
        }
    });
}
