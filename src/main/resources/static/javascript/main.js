document.addEventListener('DOMContentLoaded', function () {
    const odontologosLink = document.getElementById('odontologosLink');
    const pacientesLink = document.getElementById('pacientesLink');
    const turnosLink = document.getElementById('turnosLink');
    const mainContent = document.getElementById('mainContent');

    odontologosLink.addEventListener('click', function () {
        fetchApiAndDisplayData('http://localhost:8080/api/odontologo');
    });

    pacientesLink.addEventListener('click', function () {
        fetchApiAndDisplayData('http://localhost:8080/api/paciente');
    });

    turnosLink.addEventListener('click', function () {
        fetchApiAndDisplayData('http://localhost:8080/api/turno');
    });

    function fetchApiAndDisplayData(apiUrl) {
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => displayData(data))
            .catch(error => console.error('Error fetching data:', error));
    }

    function displayData(data) {
        mainContent.innerHTML = ''; // Limpiar contenido existente

        if (data && data.length > 0) {
            const table = document.createElement('table');
            table.classList.add('data-table');

            // Crear encabezados de tabla
            const headers = Object.keys(data[0]);
            const headerRow = document.createElement('tr');
            headers.forEach(header => {
                const th = document.createElement('th');
                th.textContent = header;
                headerRow.appendChild(th);
            });
            table.appendChild(headerRow);

            // Crear filas de datos
            data.forEach(item => {
                const row = document.createElement('tr');
                headers.forEach(header => {
                    const td = document.createElement('td');
                    td.textContent = item[header];
                    row.appendChild(td);
                });
                table.appendChild(row);
            });

            mainContent.appendChild(table);
        } else {
            mainContent.textContent = 'No hay datos disponibles.';
        }
    }
});
