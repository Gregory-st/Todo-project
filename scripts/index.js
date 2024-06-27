function onClick(event, x){
    event.preventDefault();

    const parantControll = x
    .parentNode
    .parentNode
    .parentNode
    .querySelector(".id");

    const url = window.location.href;
    const data = {
        "id": parseInt(parantControll.textContent),
        "status": true
    };

    const jwt = sessionStorage.getItem('jwt');

    fetch(url, {

        method: 'POST',

        headers: {

            'Authorization': `Bearer ${jwt}`,
            'Content-Type': 'application/json'

        },

        body: JSON.stringify(data)

    })
    .then(response => response.json())
        .then(data => {

            if(!data.success) return;

            location.href = location.href.substring(0, location.href.length - 1);
        })
        .finally(() => location.reload());

}

function(event, btn){
    event.preventDefault();

    const parentControl = btn
        .parentNode
        .parentNode
        .parentNode
        .querySelector(".id");

    const url = window.location.href + "/" + parentControl.textContent;

    fetch(url, {
        method = "DELETE"
    })
    .then(response => response.json())
    .then(resp => console.log(resp));
}

document.addEventListener("DOMContentLoaded", () =>{

    const sorter = document.getElementById("sort-patter");
    sorter.value = localStorage.getItem('selectSort');

    document
    .querySelectorAll(".submit-done")
    .forEach(x => {
        x.addEventListener("click", event => onClick(event, x));
    });

    sorter.addEventListener('change', (event) => {

        const changeValue = event.target.value;
        const urlPatch = window.location.href + `/${changeValue}`;
        const jwt = localStorage.getItem('jwt');

        fetch(urlPatch, {
            method:'PATCH',
            headers:{
                'Authorization': `Bearer ${jwt}`
            }
        })
            .then(response => response.json())
            .then(resp => {
                localStorage.setItem('selectSort', changeValue);
                location.reload();
            })
            .catch(error => console.log(error));

    });

    const today = new Date();
    const options = {
        day:'numeric',
        month:'long',
        year:'numeric'
    };
    const formatter = new Intl.DateTimeFormat('ru-Ru', options);
    const formattedDate = formatter.format(today);

    document.querySelector(".current-month").textContent = formattedDate;
});