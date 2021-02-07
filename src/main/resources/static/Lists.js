`use strict`;

const ListName = document.querySelector("#name");
const RListId = document.querySelector("#RListID");
const UListId = document.querySelector("#UListID");
const DListId = document.querySelector("#DListID");
const peeps = document.querySelector("#peeps");
const clearHistory = document.querySelector("#clearHistory");

const clearH = () => {
    peeps.innerHTML = "";
}

clearHistory.addEventListener("click", clearH);

const createList = () => {
    const ListName = ListName.value;

    let data = {
        ListName: ListName,
    }
    fetch("#", {
        method: "POST",
        body: JSON.stringify(data),
        headers: { "Content-Type": "application/json" }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
        })
        .catch(err => console.error(`Error ${err}`));
}

const updateList = () => {
    const ListName = ListName.value;
    const ListID = UListId.value;

    let data = {
        id: ListID,
        Name: ListName
    }
    fetch(`#/${ListID}`, {
        method: "PUT",
        body: JSON.stringify(data),
        headers: { "Content-Type": "application/json" }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
        })
        .catch(err => console.error(`Error ${err}`));
}

const readOne = () => {
    const ListID = RListId.value;
    fetch(`https://reqres.in/api/users/${ListID}`)
        .then((response) => {
            if (response.status !== 200) {
                throw new Error("I don't have a status of 200");
            } else {
                console.log(response);
                console.log(`response is OK (200)`)
                response.json().then((infofromserver) => {
                    console.log(infofromserver);
                    console.log(infofromserver.data);
                    for (let users in infofromserver.data) {
                        console.log(users);
                        console.log(infofromserver.data[users])
                        let user = document.createElement("p");
                        let text = document.createTextNode(`${users} : ${infofromserver.data[users]}`);
                        user.appendChild(text);
                        peeps.appendChild(user)
                    }
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}

const readAll = () => {
    fetch("https://reqres.in/api/users")
        .then((response) => {
            if (response.status !== 200) {
                throw new Error("I don't have a status of 200");
            } else {
                console.log(response);
                console.log(`response is OK (200)`)
                response.json().then((infofromserver) => {
                    console.log(infofromserver);
                    console.log(infofromserver.data);
                    for (let users of infofromserver.data) {
                        console.log(users);
                        for (let object in users) {
                            console.log(object)
                            console.log(users[object])
                            let user = document.createElement("p");
                            let text = document.createTextNode(`${object} : ${users[object]}`);
                            user.appendChild(text);
                            peeps.appendChild(user);
                        }

                    }
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}

const deleteList = () => {
    const ListID = DListID.value;

    let data = {
        id: ListID
    }

    fetch(`https://reqres.in/api/users/${ListID}`, {
        method: "DELETE",
        headers: { "Content-Type": "application/json" }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
        })
        .catch(err => console.error(`Error ${err}`));
}




