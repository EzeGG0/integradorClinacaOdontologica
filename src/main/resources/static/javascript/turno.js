// Función para cargar las opciones de pacientes desde el backend
function cargarOpcionesPacientes() {
    fetch('/api/paciente')  // Reemplaza '/api/pacientes' con la URL correcta de tu backend
        .then(response => response.json())
        .then(data => {
            const pacienteSelect = document.getElementById('paciente');

            // Limpiar opciones existentes
            pacienteSelect.innerHTML = '';

            // Agregar nuevas opciones desde el backend
            data.forEach(paciente => {
                const option = document.createElement('option');
                option.value = paciente.id;
                option.text = paciente.name + " " + paciente.lastName;  // Ajusta según la estructura de tu objeto paciente
                pacienteSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error al cargar opciones de pacientes:', error);
        });
}

// Función para cargar las opciones de odontólogos desde el backend
function cargarOpcionesOdontologos() {
    fetch('/api/odontologo')  // Reemplaza '/api/odontologos' con la URL correcta de tu backend
        .then(response => response.json())
        .then(data => {
            const odontologoSelect = document.getElementById('odontologo');

            // Limpiar opciones existentes
            odontologoSelect.innerHTML = '';

            // Agregar nuevas opciones desde el backend
            data.forEach(odontologo => {
                const option = document.createElement('option');
                option.value = odontologo.id;
                option.text = odontologo.name + " "  + odontologo.lastName;  // Ajusta según la estructura de tu objeto odontologo
                odontologoSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error al cargar opciones de odontólogos:', error);
        });
}

// Funcoin para formateo de fecha
function formatearFecha(fecha) {
    const date = new Date(fecha);
    const formattedFecha = date.toISOString().slice(0, 16).replace("T", " ");
    return formattedFecha;
}

// Función para crear un nuevo turno
function crearTurno() {
    const fechaHora = document.getElementById('fechaHora').value;
    const pacienteSeleccionado = document.getElementById('paciente').value;
    const odontologoSeleccionado = document.getElementById('odontologo').value;

    // Formateo fecha
    const formattedFechaHora = formatearFecha(fechaHora);

    // Puedes realizar validaciones adicionales aquí si es necesario

    const nuevoTurno = {
        day: formattedFechaHora,
        pacienteSeleccionado: pacienteSeleccionado,
        odontologoSeleccionado: odontologoSeleccionado
    };

    // Realizar la solicitud al backend para crear el turno
    fetch('/api/turno/post', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(nuevoTurno),
    })
        .then(response => response.json())
        .then(data => {
            // Manejar la respuesta del backend, por ejemplo, mostrar un mensaje de éxito
            alert('Turno creado con éxito');

            // Limpiar el formulario después de la creación exitosa
            document.getElementById('turno-form').reset();

            // Recargar las opciones de pacientes y odontólogos después de la creación exitosa
            cargarOpcionesPacientes();
            cargarOpcionesOdontologos();
        })
        .catch((error) => {
            console.error('Error al crear el turno:', error);
            // Manejar errores, por ejemplo, mostrar un mensaje de error
            alert('Error al crear el turno');
        });
}

// Función para cargar todos los turnos desde el backend y mostrarlos en la interfaz
function cargarTurnos() {
    const listaTurnos = document.getElementById('lista-turnos');
    listaTurnos.innerHTML = ''; // Limpiar la lista antes de cargar los nuevos turnos

    // Obtener los turnos desde el backend (puedes ajustar esta lógica según tu implementación)
    fetch('/api/turno')
        .then(response => response.json())
        .then(turnos => {
            // Iterar sobre los turnos y agregarlos a la lista
            turnos.forEach(turno => {
                const listItem = document.createElement('li');
                listItem.innerHTML = `<span>${turno.day}</span> - <span>Paciente: ${turno.turnoPaciente.name} ${turno.turnoPaciente.lastName}</span> - <span>Odontologo: ${turno.odontologo.name} ${turno.odontologo.lastName}</span> <button onclick="eliminarTurno(${turno.id})">Eliminar</button>`;
                listaTurnos.appendChild(listItem);
            });
        })
        .catch(error => {
            console.error('Error al cargar los turnos:', error);
        });
}

// Función para eliminar un turno por su ID
function eliminarTurno(id) {
    fetch(`/api/turno/${id}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.status === 200) {
                // No Content - Eliminación exitosa sin contenido
                alert('Turno eliminado con éxito');
            } else {
                // Otro código de estado - analizar el cuerpo JSON
                return response.json();
            }
        })
        .then(data => {
            // Recargar la lista de turnos después de la eliminación exitosa
            cargarTurnos();
        })
        .catch(error => {
            alert("El turno no  pudo ser eleminado");
        });
}

// Función principal para cargar las opciones de pacientes, odontólogos y turnos al cargar la página
function inicializarPagina() {
    cargarOpcionesPacientes();
    cargarOpcionesOdontologos();
    cargarTurnos();
}

// Llamar a la función de inicialización al cargar la página
document.addEventListener('DOMContentLoaded', inicializarPagina);
