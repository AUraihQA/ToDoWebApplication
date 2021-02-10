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
const DToDoId = document.querySelector("#DToDoID");
const space = document.querySelector("#space");
const clearHistory = document.querySelector("#clearHistory");

const clearH = () => {
    space.innerHTML = "";
}

clearHistory.addEventListener("click", clearH);

const createToDo = () => {
    const Description = Cdescription.value;
    const Datecreated = CdateCreated.value;
    const Deadlinedate = CdeadlineDate.value;
    const Completion = CCompletion.value;
    const ListId = CListID.value;

    let data = {
        description: Description.value,
        dateCreated: Datecreated.value,
        deadlineDate: Deadlinedate.value,
        completion: Completion.value,
        myList: {
            id: ListId.value
        }
    }

    console.log(JSON.stringify(data))
    fetch("http://127.0.0.1:8080/ToDo/create", {
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
    const Description = Udescription;
    const Datecreated = UdateCreated;
    const Deadlinedate = UdeadlineDate;
    const Completion = UCompletion;
    const ListId = UListID;

    let data = {
        id: ToDoID.value,
        description: Description.value,
        dateCreated: Datecreated.value,
        deadlineDate: Deadlinedate.value,
        completion: Completion.value,
        myList: {
            id: ListId.value
        }
    }
	console.log(JSON.stringify(data))
    fetch(`http://127.0.0.1:8080/ToDo/update/${UListID}`, {
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
    fetch(`http://127.0.0.1:8080/ToDo/read/${RToDoID}`)
        .then((response) => {
            if (response.status !== 200) {
                throw new Error("I don't have a status of 200");
            } else {
                console.log(response);
                console.log(`response is OK (200)`)
                response.json().then((infofromserver) => {
                    console.log(infofromserver);
                    console.log(infofromserver);
                    for (let users in infofromserver) {
                        console.log(users);
                        console.log(infofromserver[users])
                        let user = document.createElement("p");
                        let text = document.createTextNode(`${users} : ${infofromserver[users]}`);
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
    fetch("http://127.0.0.1:8080/ToDo/getToDo")
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
    const DToDoID = DToDoId.value;

    let data = {
        id: DToDoID
    }

    fetch(`http://127.0.0.1:8080/ToDo/delete/${DToDoID}`, {
        method: "DELETE",
    })
        // .then(response => response.json())
        .then(info => {
            console.log(info);
        })
        .catch(err => console.error(`Error ${err}`));
}


