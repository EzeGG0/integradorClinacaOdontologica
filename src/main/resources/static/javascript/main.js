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

                    // Convertir objetos a cadena JSON con formato
                    if (typeof value === 'object') {
                        td.textContent = JSON.stringify(value, null, 2);
                    } else {
                        td.textContent = value;
                    }

                    row.appendChild(td);
                });
                table.appendChild(row);
            });

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

