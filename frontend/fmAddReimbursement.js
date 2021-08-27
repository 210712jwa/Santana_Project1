let submitButton = document.getElementById('submit');
let amountInput = document.getElementById('amount');
let descriptionInput = document.getElementById('description');
let typeInput = document.getElementById('type');
let logoutButton = document.getElementById('logout');

function checkIfUserCurrentlyLoggedIn(event){
    fetch ('http://127.0.0.1:7000/currentuser',{
        'credentials' : 'include',
        'method' : 'GET'
    }).then((response) => {
        if(response.status === 401){
            window.location.href = 'index.html'
        } else if (response.status === 200){
           return response.json();
            }
        })

}

function submit(event) {
    event.preventDefault();

    const reimbursementInfo = {
        'reimAmount': amountInput.value,
        'reimDescription': descriptionInput.value,
        'reimType': typeInput.value
    };

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
        return fetch(`http://127.0.0.1:7000/user/${user.id}/reimbursement`, {
            'method': 'POST', 
            'credentials': 'include',
            headers: {
                'Content-Type': 'application/json' 
            },
            body: JSON.stringify(reimbursementInfo)
        });
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = 'fmAddReimbursement.html';
        }else {
            alert("error adding reimburesment record");
        }
    })
};

function logout(event){
  
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


logoutButton.addEventListener('click', logout);
submitButton.addEventListener('click', submit);
window.addEventListener('load', checkIfUserCurrentlyLoggedIn)