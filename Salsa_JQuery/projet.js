 $(document).ready(function() {
	 // GET sans parametre
    $.ajax({
        url: "http://localhost:8080/showAllPassePaginated?page=0&size=3"
    }).then(function(data) {
       $('#idTotalRecords').html("#idTotalRecords = " + data.totalRecords);
	   $('#idNomPasse0').html("#idNomPasse0 = " + data.passes[0].nom); 
	   $('#idNombrePasses').html("#idNombrePasses = " + data.passes.length);
    });
	
	// GET avec parametres
	$.ajax({
        url: "http://localhost:8080/showAllNiveau"
    }).then(function(data) {
		$.each(data, function (i, item) {
			$('#idSelectNiveaux').append($('<option>', { 
				value: item.idniveau,
				text : item.codeniveau 
			}));
		});
	   //$("#idSelectNiveaux").append('<option value=1>My option</option>');
    });
	
	// POST avec parametres
	var niveau = {
		"idniveau": 1,
		"codeniveau": "Debutant"
	};
	var passe = {
		id: "", 
		niveau: niveau,
		typepasse: {"idtypepasse": 1, "codetypepasse": "Comparsa"},
		nom: "nom passe",
		cavalier: "la cavalier fait ceci",
		cavaliere: "la cavalière fait cela",
		video: ""
	};

	$.ajax({
		dataType: "application/json",
        contentType: "application/json;charset=ISO-8859-1",
		type: "POST",
		url: "http://localhost:8080/createPasse",
		data: JSON.stringify(passe),
		success: function (response) {
			alert(JSON.stringify(response));
		},
		failure: function (response) {
			alert(response);
		}
	});
	
});