const app = document.getElementById('app');
const addPacienteForm = document.getElementById('addPacienteForm');
const pacienteList = document.getElementById('pacienteList');

async function obtenerPacientes() {
    try {
        const response = await fetch('http://localhost:8080/api/paciente');
        const pacientes = await response.json();

        pacienteList.innerHTML = '';

        pacientes.forEach(paciente => {
            const listItem = document.createElement('div');
            listItem.classList.add('paciente-item');

            const pacienteInfo = document.createElement('div');
            pacienteInfo.classList.add('paciente-info');
            pacienteInfo.innerHTML = `<div><strong>ID:</strong> ${paciente.id}</div>
                                      <div><strong>Nombre:</strong> ${paciente.name}</div>
                                      <div><strong>Apellido:</strong> ${paciente.lastName}</div>
                                      <div><strong>DNI:</strong> ${paciente.dni}</div>
                                      <div><strong>Dirección:</strong> ${paciente.address.calle}, ${paciente.address.numero}, ${paciente.address.localidad}, ${paciente.address.provincia}</div>`;

            const deleteButton = document.createElement('button');
            deleteButton.textContent = 'Eliminar';
            deleteButton.onclick = () => eliminarPaciente(paciente.id);
            deleteButton.classList.add('btn-eliminar');
            pacienteInfo.appendChild(deleteButton);

            const updateButton = document.createElement('button');
            updateButton.textContent = 'Actualizar';
            updateButton.onclick = () => mostrarFormularioActualizar(paciente);
            updateButton.classList.add('btn-actualizar');
            pacienteInfo.appendChild(updateButton);

            listItem.appendChild(pacienteInfo);

            pacienteList.appendChild(listItem);
        });
    } catch (error) {
        console.error('Error al obtener la lista de pacientes:', error);
    }
}


async function agregarPaciente() {
    try {
        const nuevoPaciente = obtenerDatosFormulario();

        const response = await fetch('http://localhost:8080/api/paciente', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(nuevoPaciente),
        });

        if (response.ok) {
            await obtenerPacientes();
            addPacienteForm.reset();
        } else {
            console.error('Error al agregar el paciente:', response.status, response.statusText);
        }
    } catch (error) {
        console.error('Error al agregar el paciente:', error);
    }
}

async function eliminarPaciente(id) {
    try {
        const response = await fetch(`http://localhost:8080/api/paciente/${id}`, {
            method: 'DELETE',
        });

        if (response.ok) {
            await obtenerPacientes();
        } else {
            console.error('Error al eliminar el paciente:', response.status, response.statusText);
        }
    } catch (error) {
        console.error('Error al eliminar el paciente:', error);
    }
}

async function buscarPacientePorId(id) {
    try {
        const response = await fetch(`http://localhost:8080/api/paciente/${id}`);
        const paciente = await response.json();

        mostrarFormularioActualizar(paciente);
    } catch (error) {
        console.error('Error al buscar el paciente por ID:', error);
    }
}

function mostrarFormularioActualizar(paciente) {
    const updatePacienteForm = document.createElement('form');
    updatePacienteForm.id = 'updatePacienteForm';

    const idInput = document.createElement('input');
    idInput.type = 'hidden';
    idInput.name = 'id';
    idInput.value = paciente.id;

    const nameLabel = document.createElement('label');
    nameLabel.textContent = 'Nombre:';
    const nameInput = document.createElement('input');
    nameInput.type = 'text';
    nameInput.name = 'name';
    nameInput.value = paciente.name;

    const lastNameLabel = document.createElement('label');
    lastNameLabel.textContent = 'Apellido:';
    const lastNameInput = document.createElement('input');
    lastNameInput.type = 'text';
    lastNameInput.name = 'lastName';
    lastNameInput.value = paciente.lastName;

    const dniLabel = document.createElement('label');
    dniLabel.textContent = 'DNI:';
    const dniInput = document.createElement('input');
    dniInput.type = 'text';
    dniInput.name = 'dni';
    dniInput.value = paciente.dni;

    const calleLabel = document.createElement('label');
    calleLabel.textContent = 'Calle:';
    const calleInput = document.createElement('input');
    calleInput.type = 'text';
    calleInput.name = 'calle';
    calleInput.value = paciente.address.calle;

    const numeroLabel = document.createElement('label');
    numeroLabel.textContent = 'Número:';
    const numeroInput = document.createElement('input');
    numeroInput.type = 'text';
    numeroInput.name = 'numero';
    numeroInput.value = paciente.address.numero;

    const localidadLabel = document.createElement('label');
    localidadLabel.textContent = 'Localidad:';
    const localidadInput = document.createElement('input');
    localidadInput.type = 'text';
    localidadInput.name = 'localidad';
    localidadInput.value = paciente.address.localidad;

    const provinciaLabel = document.createElement('label');
    provinciaLabel.textContent = 'Provincia:';
    const provinciaInput = document.createElement('input');
    provinciaInput.type = 'text';
    provinciaInput.name = 'provincia';
    provinciaInput.value = paciente.address.provincia;

    const updateButton = document.createElement('button');
    updateButton.textContent = 'Actualizar';
    updateButton.type = 'button';
    updateButton.onclick = () => actualizarPaciente();
    updatePacienteForm.append(
        idInput,
        nameLabel, nameInput,
        lastNameLabel, lastNameInput,
        dniLabel, dniInput,
        calleLabel, calleInput,
        numeroLabel, numeroInput,
        localidadLabel, localidadInput,
        provinciaLabel, provinciaInput,
        updateButton
    );

    // Agrega el formulario a la página
    app.appendChild(updatePacienteForm);
}

async function actualizarPaciente() {
    try {
        const updatedPaciente = obtenerDatosFormulario();

        const response = await fetch(`http://localhost:8080/api/paciente`, {
            method: 'PUT', // o 'PATCH' dependiendo de tu lógica de actualización
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(updatedPaciente),
        });

        if (response.ok) {
            await obtenerPacientes();
            // Elimina el formulario de actualización después de la actualización exitosa
            const updatePacienteForm = document.getElementById('updatePacienteForm');
            if (updatePacienteForm) {
                updatePacienteForm.remove();
            }
        } else {
            console.error('Error al actualizar el paciente:', response.status, response.statusText);
        }
    } catch (error) {
        console.error('Error al actualizar el paciente:', error);
    }
}

function obtenerDatosFormulario() {
    return {
        id: document.getElementsByName('id')[0].value,
        name: document.getElementsByName('name')[0].value,
        lastName: document.getElementsByName('lastName')[0].value,
        dni: document.getElementsByName('dni')[0].value,
        address: {
            calle: document.getElementsByName('calle')[0].value,
            numero: document.getElementsByName('numero')[0].value,
            localidad: document.getElementsByName('localidad')[0].value,
            provincia: document.getElementsByName('provincia')[0].value,
        }
    };
}

async function buscarPacientePorId() {
    try {
        const idBuscar = document.getElementById('idBuscar').value;

        if (!idBuscar) {
            console.error('Por favor, ingrese un ID válido.');
            return;
        }

        const response = await fetch(`http://localhost:8080/api/paciente/${idBuscar}`);
        const pacientePorIdResult = document.getElementById('pacientePorIdResult');
        if (response.ok) {
            const paciente = await response.json();


            pacientePorIdResult.innerHTML = `<strong>ID:</strong> ${paciente.id}<br>
                                             <strong>Nombre:</strong> ${paciente.name}<br>
                                             <strong>Apellido:</strong> ${paciente.lastName}<br>
                                             <strong>DNI:</strong> ${paciente.dni}<br>
                                             <strong>Dirección:</strong> ${paciente.address.calle}, ${paciente.address.numero}, ${paciente.address.localidad}, ${paciente.address.provincia}`;
        } else {
            console.error('Error al buscar el paciente por ID:', response.status, response.statusText);
        }
    } catch (error) {
        console.error('Error al buscar el paciente por ID:', error);
    }
}

// Inicializar la interfaz de usuario
function iniciarUI() {
    obtenerPacientes();
}

window.addEventListener('load', iniciarUI);
