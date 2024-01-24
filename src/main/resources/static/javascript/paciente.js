// Función para realizar una solicitud HTTP
async function hacerSolicitud(url, metodo, datos = null) {
    try {
        const respuesta = await fetch(url, {
            method: metodo,
            headers: {
                'Content-Type': 'application/json',
            },
            body: datos ? JSON.stringify(datos) : null,
        });

        if (!respuesta.ok) {
            const mensajeError = await respuesta.text();
            throw new Error(`Error ${respuesta.status}: ${mensajeError}`);
        }

        return await respuesta.json();
    } catch (error) {
        console.error('Error en la solicitud:', error);
        throw error;
    }
}

// Función para cargar la lista de pacientes desde el backend
async function cargarPacientes() {
    try {
        const pacientes = await hacerSolicitud('/api/paciente', 'GET');
        actualizarListaPacientes(pacientes);
    } catch (error) {
        console.error('Error al cargar pacientes:', error);
    }
}

// Función para agregar un nuevo paciente
async function agregarPaciente() {
    const nombre = document.getElementById('nombre').value;
    const apellido = document.getElementById('apellido').value;
    const fechaDeAlta = document.getElementById('fechaDeAlta').value;
    const dni = document.getElementById('dni').value;
    const calle = document.getElementById('calle').value;
    const numero = document.getElementById('numero').value;
    const localidad = document.getElementById('localidad').value;
    const provincia = document.getElementById('provincia').value;

    const nuevoPaciente = {
        name: nombre,
        lastName: apellido,
        fechaDeAlta: fechaDeAlta,
        dni: dni,
        address: {
            calle: calle,
            numero: numero,
            localidad: localidad,
            provincia: provincia
        }
    };

    try {
        await hacerSolicitud('/api/paciente', 'POST', nuevoPaciente);
        limpiarFormulario();
        cargarPacientes();
    } catch (error) {
        console.error('Error al agregar paciente:', error);
    }
}

// Función para cargar datos en el formulario de actualización
function cargarDatosEnFormulario(paciente) {
    const listaPacientes = document.getElementById('pacientesList');

    // Encuentra el elemento de la lista correspondiente al paciente
    const listItem = Array.from(listaPacientes.children).find(item => item.dataset.id === paciente.id.toString());

    if (listItem) {
        // Deshabilitar botones de actualizar y eliminar
        listItem.querySelector('.btn-actualizar').disabled = true;
        listItem.querySelector('.btn-eliminar').disabled = true;

        // Crear campos de entrada para actualizar
        const inputNombre = document.createElement('input');
        inputNombre.type = 'text';
        inputNombre.value = paciente.name;

        const inputApellido = document.createElement('input');
        inputApellido.type = 'text';
        inputApellido.value = paciente.lastName;

        const inputFechaDeAlta = document.createElement('input');
        inputFechaDeAlta.type = 'date';
        inputFechaDeAlta.value = paciente.fechaDeAlta;

        const inputDni = document.createElement('input');
        inputDni.type = 'text';
        inputDni.value = paciente.dni;

        const inputCalle = document.createElement('input');
        inputCalle.type = 'text';
        inputCalle.value = paciente.address.calle;

        const inputNumero = document.createElement('input');
        inputNumero.type = 'text';
        inputNumero.value = paciente.address.numero;

        const inputLocalidad = document.createElement('input');
        inputLocalidad.type = 'text';
        inputLocalidad.value = paciente.address.localidad;

        const inputProvincia = document.createElement('input');
        inputProvincia.type = 'text';
        inputProvincia.value = paciente.address.provincia;

        // Agregar campos de entrada a la lista
        listItem.querySelector('.nombre').appendChild(inputNombre);
        listItem.querySelector('.apellido').appendChild(inputApellido);
        listItem.querySelector('.fechaDeAlta').appendChild(inputFechaDeAlta);
        listItem.querySelector('.dni').appendChild(inputDni);
        listItem.querySelector('.calle').appendChild(inputCalle);
        listItem.querySelector('.numero').appendChild(inputNumero);
        listItem.querySelector('.localidad').appendChild(inputLocalidad);
        listItem.querySelector('.provincia').appendChild(inputProvincia);

        // Crear botones de guardar y cancelar
        const btnGuardar = document.createElement('button');
        btnGuardar.textContent = 'Guardar';
        btnGuardar.addEventListener('click', () => guardarCambios(paciente.id));

        const btnCancelar = document.createElement('button');
        btnCancelar.textContent = 'Cancelar';
        btnCancelar.addEventListener('click', cancelarActualizacion);

        // Agregar botones a la lista
        listItem.querySelector('.acciones').appendChild(btnGuardar);
        listItem.querySelector('.acciones').appendChild(btnCancelar);
    }
}

// Función para guardar cambios después de actualizar
async function guardarCambios(pacienteId) {
    const listaPacientes = document.getElementById('pacientesList');
    const listItem = Array.from(listaPacientes.children).find(item => item.dataset.id === pacienteId.toString());

    if (listItem) {
        const nombre = listItem.querySelector('.nombre input').value;
        const apellido = listItem.querySelector('.apellido input').value;
        const fechaDeAlta = listItem.querySelector('.fechaDeAlta input').value;
        const dni = listItem.querySelector('.dni input').value;
        const calle = listItem.querySelector('.calle input').value;
        const numero = listItem.querySelector('.numero input').value;
        const localidad = listItem.querySelector('.localidad input').value;
        const provincia = listItem.querySelector('.provincia input').value;

        const pacienteActualizado = {
            id: pacienteId,
            name: nombre,
            lastName: apellido,
            fechaDeAlta: fechaDeAlta,
            dni: dni,
            address: {
                calle: calle,
                numero: numero,
                localidad: localidad,
                provincia: provincia
            }
        };

        try {
            await hacerSolicitud(`/api/paciente`, 'PUT', pacienteActualizado);
            cargarPacientes();
            cancelarActualizacion(); // Puedes quitar esto si prefieres que los campos de edición permanezcan después de guardar
        } catch (error) {
            console.error('Error al actualizar paciente:', error);
        }
    }
}

// Función para limpiar el formulario después de agregar un paciente
function limpiarFormulario() {
    document.getElementById('nombre').value = '';
    document.getElementById('apellido').value = '';
    document.getElementById('fechaDeAlta').value = '';
    document.getElementById('dni').value = '';
    document.getElementById('calle').value = '';
    document.getElementById('numero').value = '';
    document.getElementById('localidad').value = '';
    document.getElementById('provincia').value = '';
}

// Función para eliminar un paciente
async function eliminarPaciente(id) {
    try {
        await hacerSolicitud(`/api/paciente/${id}`, 'DELETE');
        cargarPacientes();
    } catch (error) {
        console.error('Error al eliminar paciente:', error);
    }
}

// Función para cancelar la actualización y mostrar el formulario de creación
function cancelarActualizacion() {
    cargarPacientes();
}

// Inicializar la lista de pacientes al cargar la página
window.onload = function() {
    cargarPacientes();
};
