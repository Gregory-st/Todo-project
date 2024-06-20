document.addEventListener("DOMContentLoaded", () => {

    let firstname = document.getElementById("first-name");
    let lastname = document.getElementById("last-name");
    let login = document.getElementById("login-user");
    let email = document.getElementById("email-user");
    let password = document.getElementById("pass-user");

    let controlls = [ 
        document.getElementById("first-name"),
        document.getElementById("last-name"),
        document.getElementById("login-user"),
        document.getElementById("email-user"),
        document.getElementById("pass-user")
     ];

    const buttonSubmit = document.querySelector(".submit-reg");

    buttonSubmit.addEventListener("click", (event) => {
        event.preventDefault();

        let valid = true;

        controlls.forEach(x => { 
            x.classList.remove("error");
            x.classList.remove("shake");

            if(x.value.length == 0){
                x.classList.add("error");
                x.classList.add("shake");
                valid = false;
            }
        })

        if(!valid) return;

        controlls.forEach(x => console.log(x.value));

        fetch('http://localhost:8080/v1/todo/person/register', {
        
            method: 'POST',
        
            headers: {
        
                'Content-Type': 'application/json'
        
            },
        
            body: JSON.stringify({
                firstname: controlls[0].value,
                lastname: controlls[1].value,
                login: controlls[2].value,
                email: controlls[3].value,
                password: controlls[4].value
            })
        
        })
        
            .then(response => response.json())
        
            .then(data => {

                

                window.location.href = data.redirectPath;
            })
        
            .catch(error => console.log(error));
    })
});