window.onload = function() {
    fetch('http://localhost:8080/api/employees')
        .then(response => response.json())
        .then(employees => print(employees));

    document.querySelector("#create-employee-button").onclick = function() {
        let content = document.querySelector("#employee-name").value;
        fetch('http://localhost:8080/api/employees', {method: 'POST', headers: {
                'Content-Type': 'application/json'},
            body: JSON.stringify({name: content})
        })
            .then(response => response.json())
            .then(employee => console.log(employee.id));
    };

}

function print(employees) {
    let ul = document.querySelector("#employees-ul");
    for (employee of employees) {
        let p = document.createElement("li");
        p.innerHTML = employee.name;
        ul.appendChild(p);
    }
}
