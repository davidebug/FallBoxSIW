$(document).ready(function(){
	$('#name').html('<p>aaaaaaa</p>')
		get_files("");

});
		
function get_files(currentFolder) {
//	$.ajax({
//		type: "GET",
//		url: "http://localhost:8182/FallBox/ListObjects/*", //servlet per la lista dei file
//		contentType: "json",
//		data: {currentFolder: "currentFolder"},
//		success: function(result, status, xhr) {
//			console.log("Entered: ", result);
			$('#name').html('aaaaaaa')
			//$('#fileList').find("tr:gt(0)").remove();
			//file_list = JSON.parse((xhr.responseText.trim().split('\n'))[0]);  se devo splittare la lista
			var file_list = ["Saab", "Volvo", "BMW"];
			for (var i = 0; i < file_list.length; i++) {
//				if(file_list[i].isDirectory()){
//					var row = "<tr>"
//						row += "<td><a onclick='changeDisponibility("+ file_list[i].getName +")' class='btn primary-btn fa fa-ban glyphicon'> file_list[i].getName()</td>"				
//						row += "</tr>"
//				}
//				else{
				
					var row = "<tr>"
						row += "<td> file_list[i]</td>"
					
						row += "</tr>"
//				}			
				$('#fileList tr:last').after(row);
			}
//		},
//		error: function(error) {
//			console.log("Error: ", error);
//		}
	//});
}

function change_directory(name){
	get_files(name);
}