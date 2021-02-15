`use strict`;

const CListName = document.querySelector("#Cname");
const UListName = document.querySelector("#Uname");
const RListId = document.querySelector("#RListID");
const UListId = document.querySelector("#UListID");
const DListId = document.querySelector("#DListID");
const space = document.querySelector("#space");
const space1 = document.querySelector("#space1");
const clearHistory = document.querySelector("#clearHistory");
const alert = document.querySelector("#onsuccess");

const clearH = () => {
    space.innerHTML = "";
}

clearHistory.addEventListener("click", clearH);

const createList = () => {
    const ListName = CListName.value;

    let data = {
        name: ListName
    }
    fetch("http://127.0.0.1:8080/list/create", {
        method: "POST",
        body: JSON.stringify(data),
        headers: { "Content-Type": "application/json" }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
            alert.setAttribute("class", "alert alert-success");
            alert.innerHTML = "List has been successfully created!";
            setTimeout(() => {
                alert.removeAttribute("class");
                alert.innerHTML = "";
            }, 2000);
        })
        .catch(err => console.error(`Error ${err}`));
}

const updateList = () => {
    const UListname = UListName.value;
    const UListID = UListId.value;

    let data = {
        id: UListID,
        name: UListname
    }
    fetch(`http://127.0.0.1:8080/list/update/${UListID}`, {
        method: "PUT",
        body: JSON.stringify(data),
        headers: { "Content-Type": "application/json" }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
            alert.setAttribute("class", "alert alert-success");
            alert.innerHTML = "List has been successfully updated!";
            setTimeout(() => {
                alert.removeAttribute("class");
                alert.innerHTML = "";
            }, 2000);
        })
        .catch(err => console.error(`Error ${err}`));
}

const readOne = () => {
    const RListID = RListId.value;
    fetch(`http://127.0.0.1:8080/list/read/${RListID}`)
        .then((response) => {
            if (response.status !== 200) {
                throw new Error("I don't have a status of 200");
            } else {
                console.log(response);
                console.log(`response is OK (200)`)
                response.json().then((infofromserver) => {
                    console.log(infofromserver);
                    console.log(infofromserver.toDoList);
                    for (let users of infofromserver.toDoList) {
                        console.log(users);
                        for (object in users) {
                            console.log(object);
                            console.log(users[object]);
                            let user = document.createElement("p");
                            let text = document.createTextNode(`${object} : ${users[object]}`);
                            user.appendChild(text);
                            space.appendChild(user)
                        }


                    }
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}


const readAll = () => {
    fetch("http://127.0.0.1:8080/list/getList")
        .then((response) => {
            if (response.status !== 200) {
                throw new Error("I don't have a status of 200");
            } else {
                console.log(response);
                console.log(`response is OK (200)`)
                response.json().then((infofromserver) => {
                    console.log(infofromserver);
                    for (let users of infofromserver) {
                        console.log(users);
                        console.log(users.id);
                        console.log(users.name);
                        let user = document.createElement("p");
                        let text = document.createTextNode(`ID:${users.id}  Name:${users.name}`);
                        user.appendChild(text);
                        space.appendChild(user);
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

    fetch(`http://127.0.0.1:8080/list/delete/${DListID}`, {
        method: "DELETE",
    })
        .then(info => {
            console.log(info);
            alert.setAttribute("class", "alert alert-success");
            alert.innerHTML = "List has been successfully deleted!";
            setTimeout(() => {
                alert.removeAttribute("class");
                alert.innerHTML = "";
            }, 2000);
        })
        .catch(err => console.error(`Error ${err}`));
}




