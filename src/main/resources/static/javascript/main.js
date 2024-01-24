document.addEventListener('DOMContentLoaded', function () {
    // Obtener elementos del DOM
    const odontologosLink = document.getElementById('odontologosLink');
    const pacientesLink = document.getElementById('pacientesLink');
    const turnosLink = document.getElementById('turnosLink');
    const mainContent = document.getElementById('mainContent');

    // Event listeners para los enlaces
    odontologosLink.addEventListener('click', () => cargarInformacion('/api/odontologo', 'Odontólogos'));
    pacientesLink.addEventListener('click', () => cargarInformacion('/api/paciente', 'Pacientes'));
    turnosLink.addEventListener('click', () => cargarInformacion('/api/turno', 'Turnos'));
});

function cargarInformacion(endpoint, titulo) {
    // Realizar la solicitud GET al backend
    fetch(endpoint)
        .then(response => response.json())
        .then(data => {
            // Construir la tabla con la información recibida
            const tableContainer = document.createElement('div');
            tableContainer.className = 'table-container';

            const table = document.createElement('table');
            table.className = 'data-table';

            // Crear la fila de encabezado
            const headerRow = document.createElement('tr');
            Object.keys(data[0]).forEach(key => {
                const th = document.createElement('th');
                th.textContent = key;
                headerRow.appendChild(th);
            });
            table.appendChild(headerRow);

            // Crear las filas con datos
            data.forEach(item => {
                const row = document.createElement('tr');
                Object.values(item).forEach(value => {
                    const td = document.createElement('td');

                    // Verificar si el valor es un objeto y tiene propiedades 'nombre' y 'apellido'
                    if (typeof value === 'object' && value !== null && 'name' in value && 'lastName' in value) {
                        td.textContent = `${value.name} ${value.lastName}`;
                    } else {
                        // Si no es un objeto, o es nulo, simplemente mostrar el valor
                        td.textContent = value;
                    }
                    if (typeof value === 'object' && value !== null) {
                        // Verificar si es la propiedad 'address' y extraer sus propiedades
                        if ('calle' in value && 'localidad' in value && 'provincia' in value) {
                            td.textContent = `${value.calle}, ${value.localidad}, ${value.provincia}`;
                        } else {
                            // Si es un objeto pero no es 'address', mostrar [object Object]
                            td.textContent = '[object Object]';
                        }
                    } else {
                        // Si no es un objeto, o es nulo, simplemente mostrar el valor
                        td.textContent = value;
                    }

                    row.appendChild(td);
                });
                table.appendChild(row);
            });

            // data.forEach(item => {
            //     const row = document.createElement('tr');
            //     Object.values(item).forEach(value => {
            //         const td = document.createElement('td');
            //
            //
            //
            //         row.appendChild(td);
            //     });
            //     table.appendChild(row);
            // });
            //
            tableContainer.appendChild(table);

            // Actualizar el contenido principal con la tabla
            const mainContent = document.getElementById('mainContent');
            mainContent.innerHTML = `<h2>${titulo}</h2>`;
            mainContent.appendChild(tableContainer);
        })
        .catch(error => {
            console.error(`Error al cargar la información: ${error}`);
        });
}
