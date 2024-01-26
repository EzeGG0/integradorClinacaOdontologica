document.addEventListener('DOMContentLoaded', function () {
    cargarPacientes();
});

function cargarPacientes() {
    const listaPacientes = document.getElementById('lista-pacientes');
    listaPacientes.innerHTML = '';

    // Obtener los pacientes desde el backend (ajustar según tu implementación)
    fetch('/api/paciente')
        .then(response => response.json())
        .then(pacientes => {
            pacientes.forEach(paciente => {
                const listItem = document.createElement('li');
                listItem.innerHTML = `<span>${paciente.name} ${paciente.lastName}</span> - <span>${paciente.dni}</span> <button onclick="eliminarPaciente(${paciente.id})">Eliminar</button> <button onclick="mostrarFormularioActualizar(${paciente.id})">Actualizar</button>`;
                listaPacientes.appendChild(listItem);
            });
        })
        .catch(error => {
            console.error('Error al cargar los pacientes:', error);
        });
}

function agregarPaciente() {
    const nombre = document.getElementById('nombre').value;
    const apellido = document.getElementById('apellido').value;
    const dni = document.getElementById('dni').value;

    const nuevoPaciente = {
        name: nombre,
        lastName: apellido,
        dni: dni
    };

    fetch('/api/paciente', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(nuevoPaciente),
    })
        .then(response => response.json())
        .then(data => {
            alert('Paciente creado con éxito');
            document.getElementById('paciente-form').reset();
            cargarPacientes();
        })
        .catch(error => {
            console.error('Error al crear el paciente:', error);
            alert('Error al crear el paciente');
        });
}

function eliminarPaciente(id) {
    fetch(`/api/paciente/${id}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.status === 200) {
                alert('Paciente eliminado con éxito');
            } else if (response.status === 500) {
                alert("El paciente tiene un turno")
            }
            else {
                return response.json();
            }
        })
        .then(data => {
            cargarPacientes();
        })
        .catch(error => {
            console.error('Error al eliminar el paciente:', error);
            alert('Error al eliminar el paciente');
        });
}

function mostrarFormularioActualizar(id) {
    // Obtener los datos del paciente desde el backend usando el ID (ajustar según tu implementación)
    fetch(`/api/paciente/${id}`)
        .then(response => response.json())
        .then(paciente => {
            // Llenar el formulario de actualización con los datos del paciente
            document.getElementById('actualizar-id').value = paciente.id;
            document.getElementById('actualizar-nombre').value = paciente.name;
            document.getElementById('actualizar-apellido').value = paciente.lastName;
            document.getElementById('actualizar-dni').value = paciente.dni;

            // Ajustar según la estructura de tu entidad Paciente

            document.getElementById('formulario-actualizar').style.display = 'block';
        })
        .catch(error => {
            console.error('Error al obtener los datos del paciente para actualizar:', error);
        });
}

function actualizarPaciente() {
    const id = document.getElementById('actualizar-id').value;
    const nombre = document.getElementById('actualizar-nombre').value;
    const apellido = document.getElementById('actualizar-apellido').value;
    const dni = document.getElementById('actualizar-dni').value;

    const pacienteActualizado = {
        id: id,
        name: nombre,
        lastName: apellido,
        dni: dni
    };

    fetch('/api/paciente', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(pacienteActualizado),
    })
        .then(response => {
            if (response.status === 200) {
                alert('Paciente actualizado con éxito');
                // Ocultar el formulario después de la actualización exitosa
                document.getElementById('formulario-actualizar').style.display = 'none';
            } else {
                return response.json();
            }
        })
        .then(data => {
            cargarPacientes();
        })
        .catch(error => {
            console.error('Error al actualizar el paciente:', error);
            alert('Error al actualizar el paciente');
        });
}
