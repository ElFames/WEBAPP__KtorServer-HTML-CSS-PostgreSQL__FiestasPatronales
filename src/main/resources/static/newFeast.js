//Función para cargar las provincias al campo "select".
function cargarProvincias() {
    //Inicializamos el array.
    var array = ["Cantabria", "Asturias", "Galicia", "Andalucia", "Extremadura"];
    //Ordena el array alfabeticamente.
    array.sort();
    //Pasamos a la funcion addOptions(el ID del select, las provincias cargadas en el array).
    addOptions("provincia", array);
}

//Función para agregar opciones a un <select>.
function addOptions(domElement, array) {
    var selector = document.getElementsByName(domElement)[0];
    //Recorremos el array.
    for (provincia in array) {
        var opcion = document.createElement("option");
        opcion.text = array[provincia];
        selector.add(opcion);
    }
}


//Función para cargar los pueblos al campo "select" dependiendo de la provincia elegida.
function cargarPueblos() {
    //Objeto de provincias con los pueblos correspondientes.
    var listaPueblos = {
        cantabria: ["Laredo", "Gama", "Solares", "Castillo", "Santander"],
        asturias: ["Langreo", "Villaviciosa", "Oviedo", "Gijon", "Covadonga"],
        galicia: ["Tui", "Cambados", "Redondella", "Porriño", "Ogrove"],
        andalucia: ["Dos Hermanas", "Écija", "Algeciras", "Marbella", "Sevilla"],
        extremadura: ["Caceres", "Badajoz", "Plasencia", "Zafra", "Merida"]
    }

    //Declaramos un array donde guardamos todos los elementos de tipo id=provincias e id=pueblos.
    var provincias = document.getElementById('provincia');
    var pueblos = document.getElementById('pueblo');
    //Tomamos como provinciaSeleccionada, el valor del id provincia (var provincias).
    var provinciaSeleccionada = provincias.value;

    //Se limpian los pueblos.
    pueblos.innerHTML = '<option value="">Seleccione un Pueblo...</option>'

    //Si existe provinciaSeleccionada...
    if(provinciaSeleccionada !== ""){
        //Se seleccionan los pueblos y se ordenan.
        provinciaSeleccionada = listaPueblos[provinciaSeleccionada];
        provinciaSeleccionada.sort();

        //Insertamos los pueblos mediante un FOR.
        provinciaSeleccionada.forEach(function(pueblo){
            var opcion = document.createElement('option');
            opcion.value = pueblo;
            opcion.text = pueblo;
            pueblos.add(opcion);
        });
    }
}