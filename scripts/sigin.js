document.addEventListener("DOMContentLoaded", () => {

    const buttonClick = document.querySelector(".submit-auth");
    let emailInput = document.getElementById("email-user");
    let passwordInput = document.getElementById("pass-user");

    buttonClick.addEventListener("click", (event) => {
        event.preventDefault();

        passwordInput.classList.remove("error");
        passwordInput.classList.remove("shake");
        emailInput.classList.remove("error");
        emailInput.classList.remove("shake");

        let valid = passwordInput.value.length > 0 && emailInput.value.length > 0;

        if(valid){


            fetch('http://localhost:8080/v1/todo/person/auth', {
            
                method: 'POST',
            
                headers: {
            
                    'Content-Type': 'application/json'
            
                },
            
                body: JSON.stringify({
                    email:emailInput.value,
                    password:passwordInput.value
                })
            
            })
                .then(response => response.json())

                .then(data => {
                    
                    if(!data.success){
                        window.location.href += "?err";
                        return;
                    }

                    window.location.href = data.redirectPath;

                })

                .catch(error => console.log(error));

            return;
        }

        if(emailInput.value.length == 0){
            emailInput.classList.add("error");
            emailInput.classList.add("shake");
        }

        if(passwordInput.value.length == 0){
            passwordInput.classList.add("error");
            passwordInput.classList.add("shake");
        }
    })
});