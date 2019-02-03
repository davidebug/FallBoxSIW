$(document).ready(function(){
		get_files("mySharedSpace");

});

$(document).on("click", "#mySharedSpace", function(){
    get_details("gpsapia@gmail.com/tombola.txt")
});

$(document).on("click", "#sharedWithMe", function(){
    get_files("sharedWithMe")
});

function get_files(currentFolder) {
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/FallBox/ListObjects/*", //servlet per la lista dei file
		contentType: "json",
		data: {"currentFolder": currentFolder},
		success: function(response, status, xhr) {
			console.log("Entered: ", response);
			$('#fileList').find("tr:gt(0)").remove();
	//		file_list = JSON.parse((xhr.responseText.trim().split('\n'))[0]);  
	//		var file_list = ["Saab", "Volvo", "BMW"];
//			for (var i = 0; i < file_list.length; i++) {
//				if(file_list[i].isDirectory()){
//					var row = "<tr>"
//						row += "<td><a onclick='get_details("+ file_list[i].getName +")' class='btn primary-btn fa fa-ban glyphicon'> file_list[i].getName()</td>"				
//						row += "</tr>"
//				}
//				else{
//				
//					var row = "<tr>"
//						row += "<td>" +  file_list[i] + "</td>"
//					
//						row += "</tr>"
//				}			
//				$('#fileList tr:last').after(row);
//			}
		},
		error: function(error) {
			console.log("Error: ", error);
		}
	});
}

function get_details(selected) {
	
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/FallBox/DetailsServlet/*", //servlet per la lista dei file
		data: {FILE: selected},
		success: function(response) {
			
			files = response.replace(/[\[\]"]/g,'' );
			var file_details = files.trim().split(",");
			var tmp = file_details[0].split(".");
			var type = tmp[2];
			$('#owner').html('<i class="fa fa-user-circle" aria-hidden="true" style="margin:6px"></i> Owner: &nbsp;' + file_details[3]);
			$('#type').html('<i class="fa fa-file" aria-hidden="true" style="margin:6px"></i> Type : &nbsp;'+ type);
			$('#lastChange').html('<i class="fa fa-clock-o" aria-hidden="true" style="margin:6px"></i> last change : &nbsp;' + file_details[2]);
			$('#dimensions').html('<i class="fa fa-dice-d6" aria-hidden="true" style="margin:6px"></i> Size : &nbsp;' + file_details[1]+' &nbsp; Bytes');
	//		var file_list = ["Saab", "Volvo", "BMW"];
			
		},
		error: function(response) {
			console.log("Error");
		}
	});
}
