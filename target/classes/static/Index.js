`use strict`;

const ListName = document.querySelector("#name");
const Description = document.querySelector("#description");
const DateCreated = document.querySelector("#dateCreated");
const DeadlineDate = document.querySelector("#deadlineDate");
const Completed = document.querySelector("#Completion");
const ListId = document.querySelector("#ListID");
const space = document.querySelector("#space");
const alert = document.querySelector("#onsuccess");


const createList = () => {
    const Listname = ListName.value;

    let data = {
        name: Listname
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

const createToDo = () => {
    const description = Description.value;
    const datecreated = DateCreated.value;
    const deadlinedate = DeadlineDate.value;
    const completion = Completed.value;
    const listId = ListId.value;

    let data = {
        description: description,
        dateCreated: datecreated,
        deadlineDate: deadlinedate,
        completion: completion,
        myList: {
            id: listId
        }
    }
    fetch("http://localhost:8080/ToDo/create", {
        method: "POST",
        body: JSON.stringify(data),
        headers: { "Content-Type": "application/json" }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
            alert.setAttribute("class", "alert alert-success");
            alert.innerHTML = "To Do has been successfully created!";
            setTimeout(() => {
                alert.removeAttribute("class");
                alert.innerHTML = "";
            }, 2000);
        })
        .catch(err => console.error(`Error ${err}`));
}



