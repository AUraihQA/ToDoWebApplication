`use strict`;

const CListName = document.querySelector("#Cname");
const UListName = document.querySelector("#Uname");
const RListId = document.querySelector("#RListID");
const UListId = document.querySelector("#UListID");
const DListId = document.querySelector("#DListID");
const space = document.querySelector("#space");
const clearHistory = document.querySelector("#clearHistory");

const clearH = () => {
    space.innerHTML = "";
}

clearHistory.addEventListener("click", clearH);

const createList = () => {
    const ListName = CListName.value;

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
    const UListname = UListName.value;
    const UListID = UListId.value;

    let data = {
        id: UListID,
        Name: UListname
    }
    fetch(`#/${UListID}`, {
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
    const RListID = RListId.value;
    fetch(`https://reqres.in/api/users/${RListID}`)
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
                        space.appendChild(user)
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
                            space.appendChild(user);
                        }

                    }
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}

const deleteList = () => {
    const DListID = DListId.value;

    let data = {
        id: DListID
    }

    fetch(`https://reqres.in/api/users/${DListID}`, {
        method: "DELETE",
    })
        // .then(response => response.json())
        .then(info => {
            console.log(info);
        })
        .catch(err => console.error(`Error ${err}`));
}




