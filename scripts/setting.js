document.addEventListener("DOMContentLoaded", () => {

    const afterbtn = document.querySelector(".beforb");
    const exitbtn = document.querySelector(".logout");

    const isDisabled = document.querySelector(".firstname").getAttribute("disabled");
    const url = location.href;
    
    afterbtn.addEventListener("click", (event) => {
        event.preventDefault();

        if(isDisabled != null){
            location.href = url + '/';
            return;
        }
        afterbtn.textContent = "Сохранить";

        const arrControlls = [
            document.getElementById("firstname-user"),
            document.getElementById("lastname-user"),
            document.getElementById("email-user"),
            document.getElementById("login-user"),
            document.getElementById("pass-new"),
            document.getElementById("pass-old")
        ];

        const jwt = localStorage.getItem('jwt');

        let valid = true;
        arrControlls.forEach(x => {

            x.classList.remove("error");
            x.classList.remove("shake");

            if(x.value.length == 0 && (x.getAttribute("name") != 'pass-old' && x.getAttribute("name") != 'pass-new')){
                x.classList.add("error");
                x.classList.add("shake");
                valid = false;
            }
        });

        if(arrControlls[4].value.length > 0){
            fetch(url + "/", {
                method: 'PATCH',
                headers:{
                    'Authorization': `Bearer ${jwt}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    'password':arrControlls[5].value
                })
            })
                .then(response => response.json())
                .then(resp => {
                    if(!resp.success){
                        arrControlls[5].classList.add("error");
                        arrControlls[5].classList.add("shake");
                        alert("Пароли не совподают");
                        valid = false;
                        return;
                    }
                })
                .catch(error => console.log(error));
        }

        if(!valid) return;


        const data = {
            'firstname':arrControlls[0].value,
            'lastname':arrControlls[1].value,
            'email':arrControlls[2].value,
            'login':arrControlls[3].value,
            'password':arrControlls[4].value
        };


        fetch(url, {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${jwt}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        
            .then(response => response.json())
        
            .then(resp => {

                if(!resp.success) return;

                localStorage.setItem('jwt', resp.token);
                window.location.href = resp.redirectPath;
            })

            .catch(error => console.log(error));

    });

    exitbtn.addEventListener('click', (event) => {
        event.preventDefault();

        const jwt = localStorage.getItem('jwt');

        fetch(url + "/exit", {
            method: 'PATCH',
            headers:{
                'Authorization': `Bearer ${jwt}`
            }
        })
            .then(response => response.json())
            .then(resp => {
                if(!resp.success) return;

                localStorage.setItem('jwt', resp.token);
                window.location.href = resp.redirectPath;
            })
            .catch(error => console.log(error));
    });

});