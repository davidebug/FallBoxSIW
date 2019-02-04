
var username = $('#username').text();
var fileSelected = 0;
var section = 0;
$(document).ready(function(){

	get_files("mySharedSpace");
	section = "mySharedSpace";
	
});

$('#mySharedSpace').on('click', function(){

    get_files("mySharedSpace");
    section= "mySharedSpace";
	
});

$('#sharedWithMe').on('click', function(){
    get_files("sharedWithMe");
    section= "mySharedSpace";
    
});

function get_files(currentFolder) {
	$('#fileBody').html('<tr></tr>');
	

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
						row += "<td  ><a id='"+file_list[i]+"'  onclick=get_details('" + file_list[i] + "')  class='btn primary-btn'>" + currentFile + "</td>";				
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
	
	fileSelected = selected;
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

$('#uploadMain').on('submit',function(){
	
	var filePath = $('#uploadMain').find('input[name="FILE"]').val();
	var currDirectory = "fallbox/"+user+"/"; 
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/FallBox/UploadServlet/*", //servlet per la lista dei file
		data: {
				currDirectory : currDirectory,
				filePath : filePath
			},
		success: function(response) {
			get_files(section);
			
		},
		error: function(response) {
			console.log("Error");
		}
	});
});

$('#uploadInside').on('submit',function(){
	
	var filePath = $('#uploadInside').find('input[name="FILE"]').val(); 
	currDirectory = "fallbox/" + user + "/"+fileSelected; 
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/FallBox/UploadServlet/*", //servlet per la lista dei file
		data: {
				currDirectory : currDirectory,
				filePath : filePath
			},
		success: function(response) {
			get_files(section);
			
		},
		error: function(response) {
			console.log("Error");
		}
	});
});
