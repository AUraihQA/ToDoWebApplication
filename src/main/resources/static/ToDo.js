`use strict`;

const Cdescription = document.querySelector("#Cdescription");
const CdateCreated = document.querySelector("#CdateCreated");
const CdeadlineDate = document.querySelector("#CdeadlineDate");
const CCompletion = document.querySelector("#CCompletion");
const CListID = document.querySelector("#CListID");
const UToDoID = document.querySelector("#UToDoID");
const Udescription = document.querySelector("#Udescription");
const UdateCreated = document.querySelector("#UdateCreated");
const UdeadlineDate = document.querySelector("#UdeadlineDate");
const UCompletion = document.querySelector("#UCompletion");
const UListID = document.querySelector("#UListID");
const RToDoId = document.querySelector("#RToDoID");
const DToDoID = document.querySelector("#DToDoID");
const space = document.querySelector("#space");
const clearHistory = document.querySelector("#clearHistory");

const clearH = () => {
    space.innerHTML = "";
}

clearHistory.addEventListener("click", clearH);

const createToDo = () => {
    const description = Cdescription;
    const Datecreated = CdateCreated;
    const Deadlinedate = CdeadlineDate;
    const completion = CCompletion;
    const ListId = CListID;

    let data = {
        Description: description,
        DateCreated: Datecreated,
        DeadlineDate: Deadlinedate,
        Completion: completion,
        ListID: ListId
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

const updateToDo = () => {
    const ToDoID = UToDoID;
    const description = Cdescription;
    const Datecreated = CdateCreated;
    const Deadlinedate = CdeadlineDate;
    const Completion = CCompletion;
    const ListId = CListID;

    let data = {
        id: ToDoID,
        description: description,
        dateCreated: Datecreated,
        deadlineDate: Deadlinedate,
        completion: Completion,
        listID: ListId
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
    const RToDoID = RToDoId.value;
    fetch(`https://reqres.in/api/users/${RToDoID}`)
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

const deleteToDo = () => {
    const DToDoID = DListId.value;

    let data = {
        id: DToDoID
    }

    fetch(`https://reqres.in/api/users/${DToDoID}`, {
        method: "DELETE",
    })
        // .then(response => response.json())
        .then(info => {
            console.log(info);
        })
        .catch(err => console.error(`Error ${err}`));
}


