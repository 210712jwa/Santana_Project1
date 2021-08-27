
let logoutButton = document.getElementById('logout');
let filterButton = document.getElementById('filter');
let typeInput = document.getElementById('status');

function checkIfUserCurrentlyLoggedIn(event) {
    fetch('http://127.0.0.1:7000/currentuser', {
        'credentials': 'include',
        'method': 'GET'
    }).then((response) => {
        if (response.status === 401) {
            window.location.href = 'index.html'
        } else if (response.status === 200) {
            return response.json();
        }
    }).then((user) => {
        return fetch('http://127.0.0.1:7000/reimbursement', {
            'method': 'GET',
            'credentials': 'include'
        });
    }).then((response) => {
        return response.json()
    }).then((reimbs) => {
        populateReimbursements(reimbs);
    })

}

function filtertable(event) {


    if (typeInput.value === "all") {
        cleardata();
        checkIfUserCurrentlyLoggedIn();
    } else {
        const typeInfo = {
            "reimType": typeInput
        }
        fetch('http://127.0.0.1:7000/currentuser', {
            'credentials': 'include',
            'method': 'GET'
        }).then((response) => {
            if (response.status === 401) {
                window.location.href = 'index.html'
            } else if (response.status === 200) {
                return response.json();
            }
        }).then((user) => {
            return fetch(`http://127.0.0.1:7000/reimbursement/${typeInput.value}`, {
                'method': 'GET',
                'credentials': 'include'
            });
        }).then((response) => {
            return response.json()
        }).then((reimbs) => {
            cleardata();
            populateReimbursements(reimbs);
        })
    }


}

function logout(event) {

    event.preventDefault();
    fetch('http://127.0.0.1:7000/logout', {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application.json'
        },
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = 'index.html';
        }
    })
};

function cleardata() {
    let tbody = document.querySelector('#reimbursements tbody');
    tbody.innerHTML = "";
}

function populateReimbursements(reimbursementArray) {
    let tbody = document.querySelector('#reimbursements tbody');

    for (const Reimbursement of reimbursementArray) {

        let tr = document.createElement('tr');

        let reimbursementIdTd = document.createElement('td');
        reimbursementIdTd.innerHTML = Reimbursement.id;

        let reimbursementAmountTd = document.createElement('td');
        reimbursementAmountTd.innerHTML = Reimbursement.amount;

        let reimbursementDescriptionTd = document.createElement('td');
        reimbursementDescriptionTd.innerHTML = Reimbursement.description;

        let reimbursementAuthorFirstName = document.createElement('td');
        reimbursementAuthorFirstName.innerHTML = Reimbursement.author.firstName;

        let reimbursementAuthorLastName = document.createElement('td');
        reimbursementAuthorLastName.innerHTML = Reimbursement.author.lastName;

        let reimbursementStatus = document.createElement('td');
        reimbursementStatus.innerHTML = Reimbursement.status.status;

        tr.appendChild(reimbursementIdTd);
        tr.appendChild(reimbursementAmountTd);
        tr.appendChild(reimbursementDescriptionTd);
        tr.appendChild(reimbursementAuthorFirstName);
        tr.appendChild(reimbursementAuthorLastName);
        tr.appendChild(reimbursementStatus);

        if (Reimbursement.status.status === "pending") {
            let btn = document.createElement("button");
            btn.innerHTML = "Approve";
            btn.value = "2";
            tr.appendChild(btn);
            btn.onclick = function () {
                fetch(`http://127.0.0.1:7000/reimbursement/${Reimbursement.id}/${btn.value}`, {
                    method: 'PATCH',
                    credentials: 'include',
                    headers: {
                        'Content-Type': 'application.json'
                    },
                }).then((response) => {
                    if (response.status === 200) {
                        window.location.href = 'fmViewReimbursements.html';
                    }
                })
            }



            let btn2 = document.createElement("button");
            btn2.innerHTML = "Deny";
            btn.value = "3";
            tr.appendChild(btn2);
            btn2.onclick = function () {
                fetch(`http://127.0.0.1:7000/reimbursement/${Reimbursement.id}/${btn.value}`, {
                    method: 'PATCH',
                    credentials: 'include',
                    headers: {
                        'Content-Type': 'application.json'
                    },
                }).then((response) => {
                    if (response.status === 200) {
                        window.location.href = 'fmViewReimbursements.html';
                    }
                })
            }
        }

        tbody.appendChild(tr);
    }
}


logoutButton.addEventListener('click', logout);
filterButton.addEventListener('click', filtertable);
window.addEventListener('load', checkIfUserCurrentlyLoggedIn)