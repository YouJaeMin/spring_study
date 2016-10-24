$(document).ready(function() {
	$("#btn").on("click", function() {
		$.ajax({
			type : "GET",
			dataType : "json",
			url : "process.do?data=" + $("#data").val(),
			success : viewMessage
		});
	});
});

function viewMessage(data) {
	// remove - 자신까지 지운다
	// empty - 자손들만 지운다
	$("#wrap").empty();
	// $("#wrap").children().remove();

	$("#wrap")
			.append(
					"<table><tr><td>first_name</td><td>email</td><td>hire_date</td><td>salary</td></tr></table>")

	$.each(data, function(index, value) {
		$("#wrap table").append(
				"<tr><td>" + value.first_name + "</td><td>" + value.email
						+ "</td><td>" + value.hire_date + "</td><td>"
						+ value.salary + "</td></tr>")
	});
	
	$("#data").val("");
	$("#data").focus();
	
}