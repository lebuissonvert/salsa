 $(document).ready(function() {
	 // GET avec parametre
	 getAllPassePaginated(0,3);
	
	// GET sans parametres
	getAllNiveaux();
	
	// POST avec parametres
	//createPasse();
	
	// Comportement du onchange du select des niveaux
	$('#idSelectNiveaux').on('change', function() {
		$("#idSelectNiveauxValue").html("select.value = " + this.value);
		$("#idSelectNiveauxText").html("select.text=" + $("#idSelectNiveaux option:selected").text());
	});
	
});

function getAllPassePaginated(p_page, p_size) {
    $.ajax({
        url: "http://localhost:8080/showAllPassePaginated?page="+p_page+"&size="+p_size
    }).then(function(data) {
       $('#idTotalRecords').html("#idTotalRecords = " + data.totalRecords);
	   $('#idNomPasse0').html("#idNomPasse0 = " + data.passes[0].nom); 
	   $('#idNombrePasses').html("#idNombrePasses = " + data.passes.length);
    });
}

function getAllNiveaux() {
	$.ajax({
        url: "http://localhost:8080/showAllNiveau"
    }).then(function(data) {
		var radio_html ="";
		$.each(data, function (i, item) {
			// alimentation du select
			$('#idSelectNiveaux').append($('<option>', { 
				value: item.idniveau,
				text : item.codeniveau 
			}));
			// alimentation du html du radio
			radio_html =radio_html+'<input type="radio" name="radio_niveaux" id='+item.idniveau+' value="'+item.codeniveau+'"/>' + item.codeniveau + '<br>';
		});
		// Trigger change event pour maj les div textuelles avec le contenu selectionné
		$('#idSelectNiveaux').change();
	   //$("#idSelectNiveaux").append('<option value=1>My option</option>');
	   
	   // Affichage de la radio + selection du 1er element
		$("#idDivRadioNiveaux").html(radio_html);
		$("input:radio[name='radio_niveaux']:first").prop("checked", "checked");
		// Ajout du onchange sur les radio
		$("input[name='radio_niveaux']:radio").off('change').on('change', function() {
			$("#idRadioNiveauxId").html("radio id = " + $("input:radio[name='radio_niveaux']:checked")[0].id);
			$("#idRadioNiveauxValue").html("radio value = " + $("input:radio[name='radio_niveaux']:checked")[0].value);
		});
		$("input[name='radio_niveaux']:radio").change();
    });
}

function createPasse() {
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
}