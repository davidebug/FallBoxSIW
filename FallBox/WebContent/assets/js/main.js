$(document).ready(function(){

	get_files("mySharedSpace");

	
});

$('#mySharedSpace').on('click', function(){

    get_files("mySharedSpace");

	
});

$('#sharedWithMe').on('click', function(){
    get_files("sharedWithMe");
    
    
});

function get_files(currentFolder) {
	$('#fileBody').html('<tr></tr>');
	
	var username = $('#username').text();
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/FallBox/ListObjects/*", //servlet per la lista dei file
		contentType: "json",
		data: {currentFolder: currentFolder},
		beforeSend: function(){
			$('#loader').css('visibility','visible');
		},
		success: function(response) {
			$('#loader').css('visibility','hidden');
			files = response.replace(/[\[\]"]/g,'' );
			var file_list = files.trim().split(",");
			for (var i = 0; i < file_list.length; i++) {
				
					currentFile = file_list[i].replace(username+"/","");
					var row = "<tr >";
						row += "<td  ><a   onclick=get_details('" + file_list[i] + "')  class='btn primary-btn'>" + currentFile + "</td>";				
						row += "</tr>";
						
						$('#fileList tr:last').after(row);
				}
			
		},
		error: function(error) {
			console.log("Error: ", error);
		}
		
	});
	
}

function get_details(selected) {
	
	//$("'#"+selected+"'").css('background-color','rgb(0,0,0)')
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/FallBox/DetailsServlet/*", //servlet per la lista dei file
		data: {FILE: selected},
		success: function(response) {
			$('#sidebar-wrapper2').css('visibility','visible')
			files = response.replace(/[\[\]"]/g,'' );
			var file_details = files.trim().split(",");
			if(file_details[0].endsWith("/"))
				type = "folder";
			else{
				type = file_details[0].substring(file_details[0].lastIndexOf("."));
			}
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


