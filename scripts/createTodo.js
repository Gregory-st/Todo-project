document.addEventListener("DOMContentLoaded", () => {

    let controllers = [
        document.getElementById("task-title"),
        document.getElementById("task-description"),
        document.getElementById("task-due-date"),
        document.getElementById("task-priority")
    ];

    const buttonSubmit = document.querySelector(".submit-btn");


    buttonSubmit.addEventListener("click", (event) => {
        event.preventDefault();

        let valid = true;

        controllers.forEach(x => {
            x.classList.remove("error");
            x.classList.remove("shake");

            if(x.value.length == 0){
                x.classList.add("error");
                x.classList.add("shake");
                valid = false;
            }
        });

        if(!valid) return;

        const url = window.location.href;
        const todo = {
            "title": controllers[0].value,
            "description": controllers[1].value,
            "dateExpiration":controllers[2].value,
            "priority": controllers[3].value.toUpperCase()
        };

        fetch(url, {
        
            method: 'POST',
        
            headers: {
        
                'Content-Type': 'application/json'
        
            },
        
            body: JSON.stringify(todo)
        
        })
        
            .then(response => response.json())
        
            .then(data => {

                window.location.href = data.redirectPath;
            })
        
            .catch(error => console.log(error));
    });
});