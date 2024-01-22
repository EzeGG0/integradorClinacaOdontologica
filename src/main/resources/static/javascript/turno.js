// // API URL
// const BASE_URL = 'http://localhost:8080/api/turno';
//
// // Obtener turnos
// async function getTurnos() {
//     const resp = await fetch(BASE_URL);
//     return await resp.json();
// }
//
// // Obtener turno por ID
// async function getTurno(id) {
//     const resp = await fetch(`${BASE_URL}/${id}`);
//     return await resp.json();
// }
//
// // Agregar turno
// async function addTurno(turno) {
//     let resp; // Declarar resp fuera del bloque try
//     try {
//         resp = await fetch(BASE_URL, {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify(turno)
//         });
//     } catch (e) {
//         console.log(e);
//     }
//
//     // Verificar si resp está definido antes de acceder a su propiedad json
//     return resp ? await resp.json() : null;
// }
//
// // Eliminar turno
// async function deleteTurno(id) {
//     const resp = await fetch(`${BASE_URL}/${id}`, {
//         method: 'DELETE'
//     });
//
//     return await resp.json();
// }
//
// // Actualizar turno
// async function updateTurno(id, updatedTurno) {
//     const resp = await fetch(`${BASE_URL}/${id}`, {
//         method: 'PUT',
//         headers: {
//             'Content-Type': 'application/json'
//         },
//         body: JSON.stringify(updatedTurno)
//     });
//
//     return await resp.json();
// }
//
// // Obtener y mostrar turnos
// document.getElementById('fetch-turnos').addEventListener('click', async () => {
//     const turnos = await getTurnos();
//
//     let rows = '';
//     turnos.forEach(t => {
//         rows += `
//       <tr>
//         <td>${t.id}</td>
//         <td>${t.fecha}</td>
//         <td>${t.paciente}</td>
//         <td>${t.odontologo}</td>
//         <td>
//           <button class="delete" data-id="${t.id}">Eliminar</button>
//           <button class="update" data-id="${t.id}">Actualizar</button>
//         </td>
//       </tr>
//     `;
//     });
//
//     document.querySelector('#turnos-table tbody').innerHTML = rows;
//
//     // Agregar listener para botones eliminar
//     const deleteButtons = document.querySelectorAll('.delete');
//     deleteButtons.forEach(button => {
//         button.addEventListener('click', async () => {
//             await deleteTurno(button.dataset.id);
//             getTurnos();
//         });
//     });
//
//     // Agregar listener para botones actualizar
//     const updateButtons = document.querySelectorAll('.update');
//     updateButtons.forEach(button => {
//         button.addEventListener('click', () => {
//             const turno = turnos.find(t => t.id == button.dataset.id);
//             // mostrar formulario con datos del turno
//         });
//     });
//
// });
//
// // Mostrar formulario para agregar
// document.getElementById('add-turno').addEventListener('click', () => {
//     document.getElementById('add-turno-form').style.display = 'block';
// });
//
// // Agregar turno
// document.getElementById('save-turno').addEventListener('click', async () => {
//     const fecha = document.getElementById('fecha').value;
//     const pacienteId = document.getElementById('paciente-id').value;
//     const odontologoId = document.getElementById('odontologo-id').value;
//
//     const turno = {
//         fecha,
//         pacienteId,
//         odontologoId
//     };
//
//     await addTurno(turno);
//
//     document.getElementById('add-turno-form').style.display = 'none';
//
//     getTurnos();
// });

async function obtenerPacientes() {
    try {
        const response = await fetch('http://localhost:8080/api/turno');
        const turno = await response.json();

        pacienteList.innerHTML = '';

        turno.forEach(turno => {
            const listItem = document.createElement('div');
            listItem.classList.add('paciente-item');

            const pacienteInfo = document.createElement('div');
            pacienteInfo.classList.add('paciente-info');
            pacienteInfo.innerHTML = `<div><strong>ID:</strong> ${turno.id}</div>
                                      `;

            const deleteButton = document.createElement('button');
            deleteButton.textContent = 'Eliminar';
            deleteButton.onclick = () => eliminarPaciente(turno.id);
            deleteButton.classList.add('btn-eliminar');
            pacienteInfo.appendChild(deleteButton);

            const updateButton = document.createElement('button');
            updateButton.textContent = 'Actualizar';
            updateButton.onclick = () => mostrarFormularioActualizar(turno);
            updateButton.classList.add('btn-actualizar');
            pacienteInfo.appendChild(updateButton);

            listItem.appendChild(pacienteInfo);

            pacienteList.appendChild(listItem);
        });
    } catch (error) {
        console.error('Error al obtener la lista de pacientes:', error);
    }
}
