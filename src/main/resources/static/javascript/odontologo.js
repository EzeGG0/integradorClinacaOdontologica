document.addEventListener('DOMContentLoaded', function () {
    cargarOdontologos();
});

function cargarOdontologos() {
    const listaOdontologos = document.getElementById('lista-odontologos');
    listaOdontologos.innerHTML = '';

    // Obtener los odontólogos desde el backend (ajustar según tu implementación)
    fetch('/api/odontologo')
        .then(response => response.json())
        .then(odontologos => {
            odontologos.forEach(odontologo => {
                const listItem = document.createElement('li');
                listItem.innerHTML = `<span>${odontologo.name} ${odontologo.lastName}</span> - <span>${odontologo.matricula}</span> <button onclick="eliminarOdontologo(${odontologo.id})">Eliminar</button> <button onclick="mostrarFormularioActualizar(${odontologo.id})">Actualizar</button>`;
                listaOdontologos.appendChild(listItem);
            });
        })
        .catch(error => {
            console.error('Error al cargar los odontólogos:', error);
        });
}

function agregarOdontologo() {
    const nombre = document.getElementById('nombre').value;
    const apellido = document.getElementById('apellido').value;
    const matricula = document.getElementById('matricula').value;

    const nuevoOdontologo = {
        name: nombre,
        lastName: apellido,
        matricula: matricula
    };

    fetch('/api/odontologo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(nuevoOdontologo),
    })
        .then(response => response.json())
        .then(data => {
            alert('Odontólogo creado con éxito');
            document.getElementById('odontologo-form').reset();
            cargarOdontologos();
        })
        .catch(error => {
            console.error('Error al crear el odontólogo:', error);
            alert('Error al crear el odontólogo');
        });
}

function eliminarOdontologo(id) {
    fetch(`/api/odontologo/${id}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.status === 200) {
                alert('Odontólogo eliminado con éxito');
            } else {
                return response.json();
            }
        })
        .then(data => {
            cargarOdontologos();
        })
        .catch(error => {
            console.error('Error al eliminar el odontólogo:', error);
            alert('Error al eliminar el odontólogo');
        });
}

function mostrarFormularioActualizar(id) {
    // Obtener los datos del odontólogo desde el backend usando el ID (ajustar según tu implementación)
    fetch(`/api/odontologo/${id}`)
        .then(response => response.json())
        .then(odontologo => {
            // Llenar el formulario de actualización con los datos del odontólogo
            document.getElementById('actualizar-id').value = odontologo.id;
            document.getElementById('actualizar-nombre').value = odontologo.name;
            document.getElementById('actualizar-apellido').value = odontologo.lastName;
            document.getElementById('actualizar-matricula').value = odontologo.matricula;
            document.getElementById('formulario-actualizar').style.display = 'block';
        })
        .catch(error => {
            console.error('Error al obtener los datos del odontólogo para actualizar:', error);
        });
}

function actualizarOdontologo() {
    const id = document.getElementById('actualizar-id').value;
    const nombre = document.getElementById('actualizar-nombre').value;
    const apellido = document.getElementById('actualizar-apellido').value;
    const matricula = document.getElementById('actualizar-matricula').value;

    const odontologoActualizado = {
        id: id,
        name: nombre,
        lastName: apellido,
        matricula: matricula
    };

    fetch('/api/odontologo/update', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(odontologoActualizado),
    })
        .then(response => {
            if (response.status === 200) {
                alert('Odontólogo actualizado con éxito');
                // Ocultar el formulario después de la actualización exitosa
                document.getElementById('formulario-actualizar').style.display = 'none';
            } else {
                return response.json();
            }
        })
        .then(data => {
            cargarOdontologos();
        })
        .catch(error => {
            console.error('Error al actualizar el odontólogo:', error);
            alert('Error al actualizar el odontólogo');
        });
}
